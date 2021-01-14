package com.eirlss.service.impl;


import java.util.HashMap;
import java.util.List;


import com.eirlss.service.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eirlss.dto.VehicleDto;
import com.eirlss.model.Vehicle;
import com.eirlss.repository.VehicleRepository;

@Service
@Transactional
public class VehicleServicesImpl implements VehicleServices {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public HashMap<Long, VehicleDto> listAllVehicles() {
		HashMap<Long, VehicleDto> map = new HashMap<>();
		List<Vehicle> vehicleList = vehicleRepository.findAll();

		for (Vehicle v : vehicleList) {
			VehicleDto modelVehicle = new VehicleDto();
			modelVehicle.setVehicleId(v.getVehicleId());
			modelVehicle.setTransmission(v.getTransmission());
			modelVehicle.setPricePerDay(v.getPricePerDay());
			modelVehicle.setPlateNumber(v.getPlateNumber());
			modelVehicle.setImageName(v.getImageName());
			modelVehicle.setFuelType(v.getFuelType());
			modelVehicle.setVehicleType(v.getVehicleType().getVehicleType());
			map.put(modelVehicle.getVehicleId(), modelVehicle);
		}

		return map;
	}

	@Override
	public void delete(long id) {
		vehicleRepository.deleteById(id);
	}

	@Override
	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	@Override
	public Vehicle get(long id) {
		return vehicleRepository.findById(id).get();
	}

}
