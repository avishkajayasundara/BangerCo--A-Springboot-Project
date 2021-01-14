package com.eirlss.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDto {
    private Long userId;
    private Long bookingId;
    private Long vehicleId;
    private List<Long> equipmentId;
    @JsonProperty("vehicle_list")
    private List<VehicleDto> vehicleDtos;
    @JsonProperty("equipment_list")
    private List<EquipmentDto> equipmentDtos;
    @JsonProperty("user_details")
    private ModelUser modelUser;
    private String status;
    private String requestDuration;
    private String startDate;
    private String endDate;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDuration() {
        return requestDuration;
    }

    public void setRequestDuration(String requestDuration) {
        this.requestDuration = requestDuration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public List<VehicleDto> getVehicleDtos() {
        return vehicleDtos;
    }

    public void setVehicleDtos(List<VehicleDto> vehicleDtos) {
        this.vehicleDtos = vehicleDtos;
    }

    public List<EquipmentDto> getEquipmentDtos() {
        return equipmentDtos;
    }

    public void setEquipmentDtos(List<EquipmentDto> equipmentDtos) {
        this.equipmentDtos = equipmentDtos;
    }

    public ModelUser getModelUser() {
        return modelUser;
    }

    public void setModelUser(ModelUser modelUser) {
        this.modelUser = modelUser;
    }
    public List<Long> getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(List<Long> equipmentId) {
        this.equipmentId = equipmentId;
    }

}
