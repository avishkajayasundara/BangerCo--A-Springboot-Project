package com.eirlss.controller;

import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Vehicle;
import com.eirlss.model.VehicleType;
import com.eirlss.repository.VehicleTypeRepository;
import com.eirlss.service.FirebaseStorageService;
import com.eirlss.service.impl.FirebaseStorageServiceImpl;
import com.eirlss.service.impl.VehicleServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:3000")
public class VehicleController {
    @Autowired
    private VehicleServicesImpl vehicleService;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private FirebaseStorageService storageService;

    @RequestMapping(value = "/Adminvehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<VehicleDto> adminVehicles(Model model) {

        HashMap<Long, VehicleDto> map = vehicleService.listAllVehicles();
        return map.values();
    }

    @RequestMapping(value = "/deleteVehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteVehicle(@RequestBody Vehicle vehicle) {

        vehicleService.delete(vehicle.getVehicleId());
        return "Success";

    }

    @RequestMapping(value = "/addVehicle", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = RequestMethod.POST)
    public String addVehicles(@RequestParam("file") MultipartFile file, @RequestParam("fuelType") String fuelType,
                              @RequestParam("transmission") String transmission, @RequestParam("pricePerDay") double pricePerDay,
                              @RequestParam("plateNumber") String numberPlate, @RequestParam("type") String type) throws IOException {

        Vehicle Vehicle = new Vehicle();
        VehicleType vehicleType = new VehicleType();
        if (fuelType != "" || fuelType != null) {
            Vehicle.setFuelType(fuelType);
        }

        if (transmission != "" || transmission != null) {
            Vehicle.setTransmission(transmission);
        }

        if (pricePerDay != 0.0) {
            Vehicle.setPricePerDay(pricePerDay);
        }

        if (numberPlate != "" || numberPlate != null) {
            Vehicle.setPlateNumber(numberPlate);
        }

        if (type != "" || type != null) {

            vehicleType.setVehicleTypeId(Long.parseLong(type));
            Vehicle.setVehicleType(vehicleType);
        }
        Vehicle.setAvailability("AVAILABLE");
        if (file != null) {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Vehicle.setImageName(fileName);

            try {
                Path path = Paths.get("C:\\Users\\User\\Desktop\\EIRLS\\BangerCoEirlss\\src\\main\\webapp\\images\\" + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

//                String fileName = storageService.uploadFile(file);
//                Vehicle.setImageName(fileName);


        }

        vehicleService.save(Vehicle);
        return "Successfully Added";
    }

    @RequestMapping(value = "/getAllVehicle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<VehicleDto> viewHomePage(Model model) {

        HashMap<Long, VehicleDto> map = vehicleService.listAllVehicles();
        return map.values();
    }

    @RequestMapping(value = "/getVehicleTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleType> getVehicleTypes() {
        return vehicleTypeRepository.findAll();

    }

}
