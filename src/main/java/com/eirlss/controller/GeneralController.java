package com.eirlss.controller;

import com.eirlss.auth.LoggedInUserDetails;
import com.eirlss.dto.VehicleDto;
import com.eirlss.model.User;
import com.eirlss.model.Vehicle;
import com.eirlss.service.UserService;
import com.eirlss.service.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class GeneralController {
    @Autowired
    VehicleServices vehicleServices;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView homePage(@AuthenticationPrincipal LoggedInUserDetails loggedInUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.jsp");
        List<VehicleDto> vehicleList = new ArrayList<>();
        HashMap<Long, VehicleDto> vehicleMap = vehicleServices.listAllVehicles();
        for (Map.Entry<Long, VehicleDto> entry : vehicleMap.entrySet()) {
            vehicleList.add(entry.getValue());
        }

        if (loggedInUser != null) {
            mv.addObject("user", loggedInUser.getUsername());
        }

        mv.addObject("vehicles", vehicleList);
        return mv;
    }

    @GetMapping("/vehicles")
    public ModelAndView getAllVehicles(@AuthenticationPrincipal LoggedInUserDetails loggedInUser) throws NoSuchAlgorithmException {
        User user = userService.getByUserName(loggedInUser.getUsername());
        long age = ChronoUnit.YEARS.between(user.getDateOfBirth(), LocalDate.now());

        ModelAndView mv = new ModelAndView();
        List<VehicleDto> vehicleList = new ArrayList<>();
        mv.setViewName("vehiclefleet.jsp");
        HashMap<Long, VehicleDto> vehicleMap = vehicleServices.listAllVehicles();
        for (Map.Entry<Long, VehicleDto> entry : vehicleMap.entrySet()) {
            vehicleList.add(entry.getValue());
        }

        Iterator<VehicleDto> iterator = vehicleList.iterator();
        if (age < 25) {
            while (iterator.hasNext()) {

                VehicleDto v = iterator.next();
                if (!v.getVehicleType().equals("Small Town Car"))
                    // Do something
                    iterator.remove();
            }
        }
        mv.addObject("user", loggedInUser.getUsername());
        mv.addObject("vehicles", vehicleList);
        return mv;
    }

    @GetMapping("/user-register")
    public ModelAndView loadRegistrationPage(@AuthenticationPrincipal LoggedInUserDetails loggedInUser) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("customerregistration.jsp");
        if (loggedInUser != null) {
            mv.addObject("user", loggedInUser.getUsername());
        }
        return mv;
    }
}
