package com.eirlss.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class VehicleType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long vehicletypeid;
	
	private String vehicleType;

	@JsonIgnore
	@OneToMany(mappedBy="vehicleType")
    private List<Vehicle> vehicleList;
	
	

	public VehicleType() {
		
	}

	public long getVehicleTypeId() {
		return vehicletypeid;
	}

	public void setVehicleTypeId(long vehicleTypeId) {
		this.vehicletypeid = vehicleTypeId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}
}
