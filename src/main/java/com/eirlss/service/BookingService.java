package com.eirlss.service;

import com.eirlss.dto.BookingDto;
import com.eirlss.model.Booking;
import com.eirlss.model.FlaggedLicenceHolder;
import com.eirlss.model.FraudUser;

import java.util.List;

public interface BookingService {
    BookingDto saveBooking(BookingDto bookingDto);
    List<BookingDto> findBookingsForUser(long userId);
    List<BookingDto> findAllBookings();
    BookingDto updateBooking(BookingDto bookingDto);
    void deleteBooking(long bookingId);
    Booking findBookingById(long bookingId);
    void addEquipmentToBooking(long equipmentId,long bookingId);
    void syncBlacklistedUsers(List<FlaggedLicenceHolder> users);
    boolean checkIfFraudUser(String licenseNo);
}
