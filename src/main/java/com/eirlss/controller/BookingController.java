package com.eirlss.controller;

import com.eirlss.auth.LoggedInUserDetails;
import com.eirlss.dto.BookingDto;
import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Booking;
import com.eirlss.model.User;
import com.eirlss.model.Vehicle;
import com.eirlss.service.EquipmentService;
import com.eirlss.service.UserService;
import com.eirlss.service.VehicleServices;
import com.eirlss.service.impl.BookingServiceImpl;
import com.eirlss.util.mapper.BookingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(value = "/save")
    public ModelAndView saveBooking(BookingDto bookingDto, @AuthenticationPrincipal LoggedInUserDetails loggedInUser) throws NoSuchAlgorithmException {
        User user = userService.getByUserName(loggedInUser.getUsername());
        bookingDto.setUserId(user.getUserid());
        if(bookingServiceImpl.checkIfFraudUser(user.getDrivingLinence().split("\\.")[0])){
            return new ModelAndView("redirect:/booking-error-fraud-user");
        }else{
            BookingDto booking =  bookingServiceImpl.saveBooking(bookingDto);
            if(booking != null){
                return new ModelAndView("redirect:/thank-you");
            }
            else{
                return new ModelAndView("redirect:/booking-error");
            }
        }
    }

    @GetMapping(value = "/new-booking")
    public ModelAndView loadNewBookingPage(long vehicleId, @AuthenticationPrincipal LoggedInUserDetails loggedInUser) {
        Vehicle vehicle = vehicleServices.get(vehicleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("vehicle", vehicle);
        List<String> bookingDates = new ArrayList<String>();
        for (Booking b : vehicle.getBookings()) {
            bookingDates.add(b.getStartDate().toLocalDate().toString());
            bookingDates.add(b.getEndDate().toLocalDate().toString());
        }
        mv.addObject("bookings", bookingDates);
        mv.addObject("user", loggedInUser.getUsername());
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

    @PostMapping(value = "/update")
    public ModelAndView updateBooking(BookingDto bookingDto,long bookingId) {
        bookingDto.setBookingId(bookingId);
        BookingDto b = bookingServiceImpl.updateBooking(bookingDto);
        return new ModelAndView("redirect:/user/account");
    }

    @DeleteMapping(value = "/{bookingId}")
    public void deleteBooking(@PathVariable("bookingId") long bookingId) {
        bookingServiceImpl.deleteBooking(bookingId);
    }

    @GetMapping(value = "/booking")
    public ModelAndView getBookingById(long bookingId,@AuthenticationPrincipal LoggedInUserDetails loggedInUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/updateBooking.jsp");
        Booking booking = bookingServiceImpl.findBookingById(bookingId);
        List<Booking> bookingList = booking.getVehicleList().get(0).getBookings();



        Iterator<Booking> iterator = bookingList.iterator();
        while (iterator.hasNext()) {
            Booking v = iterator.next();
            if (v.getBookingId() == bookingId) {
                // Do something
                iterator.remove();
            }
        }
        mv.addObject("user", loggedInUser.getUsername());
        mv.addObject("booking", BookingMapper.bookingToBookingDtoMapper(booking));
        mv.addObject("bookings", bookingList);
        mv.addObject("vehicle", booking.getVehicleList().get(0));
        mv.addObject("equipment",equipmentService.getAllEquipment());
        mv.addObject("bookedEq",booking.getEquipmentList());
        return mv;
    }

    @GetMapping(value = "/delete")
    public ModelAndView customerDeleteBooking(long bookingId) {
        bookingServiceImpl.deleteBooking(bookingId);
        return new ModelAndView("redirect:/user/account");
    }

    @GetMapping(value = "/addEquipment")
    public ModelAndView addEquipmentToBooking(long equipmentId,long bookingId){
        bookingServiceImpl.addEquipmentToBooking(equipmentId,bookingId);
        ModelMap model = new ModelMap();
        model.put("bookingId",bookingId);
        return new ModelAndView("redirect:/bookings/booking",model);
    }

}