package com.eirlss.service;

import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface VehicleServices {

    HashMap<Long, VehicleDto> listAllVehicles();
    void delete(long id);
    void save(Vehicle vehicle);
    Vehicle get(long id);
}
