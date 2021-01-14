package com.eirlss.service;

import com.eirlss.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto saveBooking(BookingDto bookingDto);
    List<BookingDto> findBookingsForUser(long userId);
    List<BookingDto> findAllBookings();
    BookingDto updateBooking(BookingDto bookingDto);
    void deleteBooking(long bookingId);
}
