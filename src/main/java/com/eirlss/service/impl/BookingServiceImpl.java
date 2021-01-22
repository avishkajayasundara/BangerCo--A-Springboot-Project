package com.eirlss.service.impl;

import com.eirlss.dto.BookingDto;
import com.eirlss.helper.BookingHelper;
import com.eirlss.model.*;
import com.eirlss.repository.*;
import com.eirlss.service.BookingService;
import com.eirlss.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private UserRepository userRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private FlaggedLicenceHolderRepository flaggedLicenceHolderRepository;

    @Autowired
    private FraudUserRepository fraudUserRepository;

    @Value("${email_subject}")
    private String emailSubject;
    @Value("${email_messagebody1}")
    private String messageBody1;
    @Value("${email_messagebody2}")
    private String messageBody2;
    @Value("${email_messagebody3}")
    private String messageBody3;
    @Value("${email_messagebody4}")
    private String messageBody4;
    @Value("${email_to_address}")
    private String to_address = "avishkaaj@live.com";

    @Override
    public BookingDto saveBooking(BookingDto bookingDto) {
        BookingHelper bookingHelper = new BookingHelper();
        Booking bookingResponse = null;
        BookingDto bookingDtoResponse = null;
        List<Equipment> equipmentList = null;
        User user = userRepository.findById(bookingDto.getUserId()).orElse(null);
        String[]  split = user.getDrivingLinence().split("\\.");
        String licenceNumber = user.getDrivingLinence().split("\\.")[0];
        FlaggedLicenceHolder flaggedLicenceHolder = flaggedLicenceHolderRepository.findById(licenceNumber).orElse(null);
        if (flaggedLicenceHolder != null) {
            emailService.sendEmail(emailSubject, constructEmailMessageBody(user, flaggedLicenceHolder), "system_noreply", to_address,user.getDrivingLinence());
            return null;
        } else {
            Vehicle vehicle = vehicleRepository.findById(bookingDto.getVehicleId()).orElse(null);
            if (!isEmpty(bookingDto.getEquipmentId())) {
                equipmentList = bookingDto.getEquipmentId()
                        .stream().map(this::constructEquipmentList)
                        .collect(Collectors.toList());
            }
            if (nonNull(user) && nonNull(vehicle)) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                Booking booking = new Booking();
                LocalDateTime startDate = parse(bookingDto.getStartDate() + " 00:00", formatter);
                LocalDateTime endDate = parse(bookingDto.getEndDate() + " 00:00", formatter);
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

    }

    private String constructEmailMessageBody(User user, FlaggedLicenceHolder flaggedLicenceHolder) {
        System.out.println(flaggedLicenceHolder.getDateTimeofOffence());
        return messageBody4+",\n".concat(messageBody1.concat(user.getFirstName() + " " + user.getLastName())).concat(" ").concat(messageBody2).concat(user.getDrivingLinence().split("\\.")[0]).concat(" ").concat(messageBody3+"\n")
                .concat("\nLicense Number" + flaggedLicenceHolder.getLicenseNo())
                .concat("\nStatus : " + flaggedLicenceHolder.getStatus())
                .concat("\nOffence : " + flaggedLicenceHolder.getOffence())
                .concat("\nDate of Incident : " + flaggedLicenceHolder.getDateTimeofOffence().split("\\s+")[0])
                .concat("\nTime of Offence : " + flaggedLicenceHolder.getDateTimeofOffence().split("\\s+")[1])
                .concat("\n\nThank you.\nRegards,\nBanger&Co");
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
                booking.setStartDate(parse(bookingDto.getStartDate() + " 00:00", formatter));
            if (!StringUtils.isEmpty(bookingDto.getEndDate()))
                booking.setEndDate(parse(bookingDto.getEndDate() + " 00:00", formatter));
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

    @Override
    public void syncBlacklistedUsers(List<FlaggedLicenceHolder> users) {
        flaggedLicenceHolderRepository.deleteAll();
        for(FlaggedLicenceHolder user:users){
                flaggedLicenceHolderRepository.save(user);
        }

    }

    @Override
    public boolean checkIfFraudUser(String licenseNo) {
        return fraudUserRepository.existsById(licenseNo);
    }
}
