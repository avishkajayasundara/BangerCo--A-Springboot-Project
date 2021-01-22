package com.eirlss.controller;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eirlss.dto.EquipmentDto;
import com.eirlss.model.Equipment;
import com.eirlss.service.impl.EquipmentServiceImpl;

@RestController
@RequestMapping("/equipment")
@CrossOrigin(origins = "http://localhost:3000")
public class EquipmentController {
	
@Autowired
EquipmentServiceImpl equipmentServiceImpl;

@RequestMapping(value = "/addEquipment", method = RequestMethod.POST)
public String addEquipment(@RequestBody Equipment equipment){

	equipment.setAvailability(true);
	equipmentServiceImpl.save(equipment);
	return "Successfully Added the Equipment";
	
}
@RequestMapping(value = "/getAllEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
public Collection<EquipmentDto> viewEquipment(Model model) {

	HashMap<Long, EquipmentDto> map = equipmentServiceImpl.listAllEquipments();
	return map.values();
}

@RequestMapping(value = "/deleteEquipment", produces = MediaType.APPLICATION_JSON_VALUE)
public String deleteEquipments(@RequestBody Equipment equipment) {
	
	equipmentServiceImpl.delete(equipment.getEquipmentId());
	return "Success";
	
}
}
