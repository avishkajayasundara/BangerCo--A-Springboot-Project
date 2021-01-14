package com.eirlss.dto;

import java.util.List;

public class WebScrapVehicleDetails {
    private List<WebScrapVehicleClass> webScrapVehicleClasses;

    public List<WebScrapVehicleClass> getWebScrapVehicleClasses() {
        return webScrapVehicleClasses;
    }

    public void setWebScrapVehicleClasses(List<WebScrapVehicleClass> webScrapVehicleClasses) {
        this.webScrapVehicleClasses = webScrapVehicleClasses;
    }
}
