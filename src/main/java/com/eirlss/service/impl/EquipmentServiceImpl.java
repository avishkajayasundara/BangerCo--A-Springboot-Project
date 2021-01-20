package com.eirlss.service.impl;

import java.util.HashMap;
import java.util.List;

import com.eirlss.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eirlss.dto.EquipmentDto;
import com.eirlss.model.Equipment;
import com.eirlss.repository.EquipmentRepository;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	public HashMap<Long, EquipmentDto> listAllEquipments() {
		HashMap<Long, EquipmentDto> map = new HashMap<>();
		List<Equipment> Equipmentlist = equipmentRepository.findAll();

		for (Equipment v : Equipmentlist) {
			EquipmentDto dto = new EquipmentDto();
			dto.setEquipmentId(v.getEquipmentId());
			dto.setEquipmentName(v.getEquipmentName());
			dto.setDescription(v.getDescription());
			dto.setAvailability(v.getAvailability());
			dto.setAvailableQuantity(v.getAvailableQuantity());
			map.put(v.getEquipmentId(), dto);
		}
		return map;
	}

	@Override
	public void save(Equipment equipment) {
		equipmentRepository.save(equipment);
	}

	@Override
	public void delete(long id) {
		equipmentRepository.deleteById(id);
	}

}
