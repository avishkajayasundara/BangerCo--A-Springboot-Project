package com.eirlss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eirlss.model.VehicleType;


public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

}
