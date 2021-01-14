package com.eirlss.dto;

public class WebScrapVehicleModel {

    private String vehicleModel;
    private String ratePerMonth;
    private String ratePerWeek;
    private String excessMileage;

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getRatePerMonth() {
        return ratePerMonth;
    }

    public void setRatePerMonth(String ratePerMonth) {
        this.ratePerMonth = ratePerMonth;
    }

    public String getRatePerWeek() {
        return ratePerWeek;
    }

    public void setRatePerWeek(String ratePerWeek) {
        this.ratePerWeek = ratePerWeek;
    }

    public String getExcessMileage() {
        return excessMileage;
    }

    public void setExcessMileage(String excessMileage) {
        this.excessMileage = excessMileage;
    }
}
