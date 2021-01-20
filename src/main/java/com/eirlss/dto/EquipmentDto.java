package com.eirlss.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipmentId;
	
	private String equipmentName;
	private String description;
	private boolean availability;
	private int availableQuantity;

	public EquipmentDto() {
		
	}

	public EquipmentDto(Long equipmentId, String equipmentName, String description, boolean availability, int availableQuantity) {
		this.equipmentId = equipmentId;
		this.equipmentName = equipmentName;
		this.description = description;
		this.availability = availability;
		this.availableQuantity = availableQuantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	
	
}
