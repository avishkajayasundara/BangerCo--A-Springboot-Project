package com.eirlss.controller;

import com.eirlss.auth.LoggedInUserDetails;
import com.eirlss.dto.BookingDto;
import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Booking;
import com.eirlss.model.Vehicle;
import com.eirlss.service.UserService;
import com.eirlss.service.VehicleServices;
import com.eirlss.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @Autowired
    private VehicleServices vehicleServices;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    public BookingDto saveBooking(BookingDto bookingDto,@AuthenticationPrincipal LoggedInUserDetails loggedInUser) throws NoSuchAlgorithmException {
        long id = userService.getByUserName(loggedInUser.getUsername()).getUserid();
        bookingDto.setUserId(id);
        return bookingServiceImpl.saveBooking(bookingDto);
    }

    @GetMapping(value = "/new-booking")
    public ModelAndView loadNewBookingPage(long vehicleId,@AuthenticationPrincipal LoggedInUserDetails loggedInUser) {
        Vehicle vehicle = vehicleServices.get(vehicleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("vehicle",vehicle);
        List<String> bookingDates = new ArrayList<String>();
        for(Booking b:vehicle.getBookings()){
            bookingDates.add(b.getStartDate().toLocalDate().toString());
            bookingDates.add(b.getEndDate().toLocalDate().toString());
        }
        mv.addObject("bookings",bookingDates);
        mv.addObject("user",loggedInUser.getUsername());
        mv.setViewName("/bookingpage.jsp");
        return mv;
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