package com.eirlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eirlss.model.Equipment;


public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

}
