package com.eirlss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eirlss.model.Vehicle;



public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
