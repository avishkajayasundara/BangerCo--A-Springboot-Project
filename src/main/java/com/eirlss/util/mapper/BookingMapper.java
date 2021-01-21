package com.eirlss.util.mapper;

import com.eirlss.dto.BookingDto;
import com.eirlss.dto.EquipmentDto;
import com.eirlss.dto.ModelUser;
import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Booking;
import com.eirlss.model.Equipment;
import com.eirlss.model.User;
import com.eirlss.model.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

public class BookingMapper {

    public static List<BookingDto> bookingListToBookingDtoListMapper(List<Booking> bookings) {
        return bookings.stream()
                .map(booking -> bookingToBookingDtoMapper(booking))
                .collect(Collectors.toList());
    }

    public static BookingDto bookingToBookingDtoMapper(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(booking.getBookingId());
        bookingDto.setStartDate(booking.getStartDate().toLocalDate().toString());
        bookingDto.setEndDate(booking.getEndDate().toLocalDate().toString());
        bookingDto.setStatus(booking.getState());
        if (!isEmpty(booking.getVehicleList())) {
            bookingDto.setVehicleDtos(booking.getVehicleList().
                    stream()
                    .map(BookingMapper::vehicleToVehicleDtoMapper)
                    .collect(Collectors.toList()));
        }
        if (!isEmpty(booking.getEquipmentList())) {
            bookingDto.setEquipmentDtos(booking.getEquipmentList().
                    stream()
                    .map(BookingMapper::equipmentToEquipmentDtoMapper)
                    .collect(Collectors.toList()));
        }
        if (nonNull(booking.getUser())) {
            bookingDto.setModelUser(userToModelUserMapper(booking.getUser()));
        }
        return bookingDto;
    }

    private static ModelUser userToModelUserMapper(User user) {
        ModelUser modelUser = new ModelUser();
        modelUser.setUserid(user.getUserid());
        modelUser.setUserName(user.getUserName());
        modelUser.setFirstName(user.getFirstName());
        modelUser.setLastName(user.getLastName());
        modelUser.setEmail(user.getEmail());
        modelUser.setDrivingLinence(user.getDrivingLinence());
        modelUser.setMobile(user.getContact());
        return modelUser;
    }

    private static EquipmentDto equipmentToEquipmentDtoMapper(Equipment equipment) {
        EquipmentDto equipmentDto = new EquipmentDto();
        equipmentDto.setEquipmentId(equipment.getEquipmentId());
        equipmentDto.setEquipmentName(equipment.getEquipmentName());
        equipmentDto.setDescription(equipment.getDescription());
        equipmentDto.setAvailability(equipment.getAvailability());
        return equipmentDto;
    }

    private static VehicleDto vehicleToVehicleDtoMapper(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setVehicleId(vehicle.getVehicleId());
        if (nonNull(vehicle.getVehicleType())) {
            vehicleDto.setVehicleType(vehicle.getVehicleType().getVehicleType());
        }
        vehicleDto.setPlateNumber(vehicle.getPlateNumber());
        vehicleDto.setPricePerDay(vehicle.getPricePerDay());
        vehicleDto.setFuelType(vehicle.getFuelType());
        vehicleDto.setTransmission(vehicle.getTransmission());
        vehicleDto.setImageName(vehicle.getImageName());
        return vehicleDto;
    }

}
