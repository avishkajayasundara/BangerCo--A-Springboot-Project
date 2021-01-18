package com.eirlss.controller;

import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Vehicle;
import com.eirlss.service.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {
    @Autowired
    VehicleServices vehicleServices;
    @GetMapping("/")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index.jsp");
        List<VehicleDto> vehicleList = new ArrayList<>();
        HashMap<Long, VehicleDto> vehicleMap  = vehicleServices.listAllVehicles();
        for (Map.Entry<Long, VehicleDto> entry : vehicleMap.entrySet()) {
            vehicleList.add(entry.getValue());
        }
        mv.addObject("vehicles",vehicleList);
        return mv;
    }
    @GetMapping("/vehicles")
    public ModelAndView getAllVehicles(){
        ModelAndView mv = new ModelAndView();
        List<VehicleDto> vehicleList = new ArrayList<>();
        mv.setViewName("vehiclefleet.jsp");
        HashMap<Long, VehicleDto> vehicleMap  = vehicleServices.listAllVehicles();
        for (Map.Entry<Long, VehicleDto> entry : vehicleMap.entrySet()) {
            vehicleList.add(entry.getValue());
        }
        mv.addObject("vehicles",vehicleList);
        return mv;
    }
}
