package com.eirlss.controller;

import com.eirlss.dto.BookingDto;
import com.eirlss.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto saveBooking(@RequestBody BookingDto bookingDto) {
        return bookingServiceImpl.saveBooking(bookingDto);
    }

    @GetMapping(value = "/userBooking/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingDto> findBookingsForUser(@PathVariable("userId") long userId) {
        return bookingServiceImpl.findBookingsForUser(userId);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingDto> findAllBookings() {
        return bookingServiceImpl.findAllBookings();
    }

    @PutMapping(value = "/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDto updateBooking(@RequestBody BookingDto bookingDto,@PathVariable("bookingId") long bookingId) {
        bookingDto.setBookingId(bookingId);
        return bookingServiceImpl.updateBooking(bookingDto);
    }

    @DeleteMapping(value = "/{bookingId}")
    public void deleteBooking(@PathVariable("bookingId") long bookingId) {
         bookingServiceImpl.deleteBooking(bookingId);
    }

}
