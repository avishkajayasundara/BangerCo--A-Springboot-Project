package com.eirlss.service;

import com.eirlss.dto.EquipmentDto;
import com.eirlss.model.Equipment;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface EquipmentService {
    HashMap<Long, EquipmentDto> listAllEquipments();
    void save(Equipment equipment);
    void delete(long id);
}
