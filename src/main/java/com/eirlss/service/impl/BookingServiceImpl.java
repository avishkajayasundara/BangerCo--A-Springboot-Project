package com.eirlss.service.impl;

import com.eirlss.dto.BookingDto;
import com.eirlss.exception.GenericException;
import com.eirlss.helper.BookingHelper;
import com.eirlss.model.Booking;
import com.eirlss.model.Equipment;
import com.eirlss.model.User;
import com.eirlss.model.Vehicle;
import com.eirlss.repository.BookingRepository;
import com.eirlss.repository.EquipmentRepository;
import com.eirlss.repository.UserRepository;
import com.eirlss.repository.VehicleRepository;
import com.eirlss.service.BookingService;
import com.eirlss.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.eirlss.helper.BookingHelper.isLicenseBlacklisted;
import static com.eirlss.util.mapper.BookingMapper.bookingListToBookingDtoListMapper;
import static com.eirlss.util.mapper.BookingMapper.bookingToBookingDtoMapper;
import static java.time.LocalDateTime.parse;
import static java.util.Arrays.asList;
import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private static final String USER_BLACKLISTED = "Blacklisted";
    private static final String SMALL_TOWN_CARS = "Small Town Cars";

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  VehicleRepository vehicleRepository;
    @Autowired
    private  BookingRepository bookingRepository;
    @Autowired
    private  EmailService emailService;
    @Autowired
    private  EquipmentRepository equipmentRepository;

    @Value("${email_subject}")
    private String emailSubject;
    @Value("${email_messagebody1}")
    private String messageBody1;
    @Value("${email_messagebody2}")
    private String messageBody2;
    @Value("${email_messagebody3}")
    private String messageBody3;
    @Value("${email_to_address}")
    private String to_address;

    @Override
    public BookingDto saveBooking(BookingDto bookingDto) {
        BookingHelper bookingHelper = new BookingHelper();
        Booking bookingResponse = null;
        BookingDto bookingDtoResponse = null;
        List<Equipment> equipmentList = null;
        User user = userRepository.findById(bookingDto.getUserId()).orElse(null);
//        if (nonNull(user) && isLicenseBlacklisted(user.getDrivingLinence())) {
//            emailService.sendEmail(emailSubject, constructEmailMessageBody(user), "system_noreply", to_address);
//            throw new GenericException("Blacklisted license: Booking Blocked");
//        }
//        if (nonNull(user) && bookingHelper.isFraudUser(user.getUserName())) {
//            throw new GenericException("Fraud User: User marked as fraud");
//        }
        Vehicle vehicle = vehicleRepository.findById(bookingDto.getVehicleId()).orElse(null);
        if (!isEmpty(bookingDto.getEquipmentId())) {
            equipmentList = bookingDto.getEquipmentId()
                    .stream().map(this::constructEquipmentList)
                    .collect(Collectors.toList());
        }
        if (nonNull(user) && nonNull(vehicle)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Booking booking = new Booking();
            LocalDateTime startDate = parse(bookingDto.getStartDate()+" 00:00", formatter);
            LocalDateTime endDate = parse(bookingDto.getEndDate()+" 00:00", formatter);
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setState("B");
            user.getBooking().add(booking);
            booking.setUser(user);
            vehicle.getBookings().add(booking);
            booking.setVehicleList(asList(vehicle));
            if (!isEmpty(equipmentList)) {
                equipmentList.forEach(equipment -> equipment.getBookings().add(booking));
                booking.setEquipmentList(equipmentList);
            }
            userRepository.save(user);
            vehicleRepository.save(vehicle);
            bookingResponse = bookingRepository.save(booking);
        }
        if (nonNull(bookingResponse)) {
            bookingDtoResponse = bookingToBookingDtoMapper(bookingResponse);
        }
        return bookingDtoResponse;
    }

    private String constructEmailMessageBody(User user) {
        return messageBody1.concat(user.getUserName()).concat(" ").concat(messageBody2).concat(user.getDrivingLinence()).concat(" ").concat(messageBody3);
    }

    private int getCustomerAge(User user) {
        Period period = Period.between(LocalDate.now(), user.getDateOfBirth());
        return Math.abs(period.getYears());
    }

    private Equipment constructEquipmentList(Long equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }

    @Override
    public List<BookingDto> findBookingsForUser(long userId) {
        List<Booking> bookings = bookingRepository.findByUser_Userid(userId);
        List<BookingDto> bookingDtos = null;
        if (!isEmpty(bookings)) {
            bookingDtos = bookingListToBookingDtoListMapper(bookings);
        } else {
            bookingDtos = new ArrayList<>();
        }
        return bookingDtos;
    }

    @Override
    public List<BookingDto> findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDto> bookingDtos = null;
        if (!isEmpty(bookings)) {
            bookingDtos = bookingListToBookingDtoListMapper(bookings);
        } else {
            bookingDtos = new ArrayList<>();
        }
        return bookingDtos;
    }

    @Override
    public BookingDto updateBooking(BookingDto bookingDto) {
        Booking bookingUpdateResponse = null;
        BookingDto bookingDtoResponse = null;
        List<Equipment> equipmentList = null;
        Booking booking = bookingRepository.findById(bookingDto.getBookingId()).orElse(null);
        if (nonNull(booking)) {
            Vehicle vehicle = null;
            Equipment equipment = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            if (!StringUtils.isEmpty(bookingDto.getStartDate()))
                booking.setStartDate(parse(bookingDto.getStartDate()+" 00:00", formatter));
            if (!StringUtils.isEmpty(bookingDto.getEndDate()))
                booking.setEndDate(parse(bookingDto.getEndDate()+" 00:00", formatter));
            if (bookingDto.getVehicleId() != null)
                vehicle = vehicleRepository.findById(bookingDto.getVehicleId()).orElse(null);
            if (!isEmpty(bookingDto.getEquipmentId())) {
                equipmentList = bookingDto.getEquipmentId()
                        .stream().map(this::constructEquipmentList)
                        .collect(Collectors.toList());
            }
            if (nonNull(vehicle)) {
                booking.getVehicleList().clear();
                booking.getVehicleList().add(vehicle);
            }
            if (nonNull(equipmentList)) {
                booking.getEquipmentList().clear();
                booking.getEquipmentList().addAll(equipmentList);
            }
            bookingUpdateResponse = bookingRepository.save(booking);
            if (nonNull(bookingUpdateResponse)) {
                bookingDtoResponse = bookingToBookingDtoMapper(bookingUpdateResponse);
            }
        }
        return bookingDtoResponse;
    }

    @Override
    public void deleteBooking(long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        bookingRepository.delete(booking);
    }

    @Override
    public Booking findBookingById(long bookingId) {
        return bookingRepository.findById(bookingId).get();
    }

    @Override
    public void addEquipmentToBooking(long equipmentId, long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        booking.getEquipmentList().add(equipmentRepository.findById(equipmentId).get());
        bookingRepository.save(booking);
    }
}
