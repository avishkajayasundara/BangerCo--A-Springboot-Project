package com.eirlss.dto;

import java.util.List;

public class WebScrapVehicleClass {
    private String vechicleClass;
    private List<WebScrapVehicleModel> webScrapVehicleModels;

    public String getVechicleClass() {
        return vechicleClass;
    }

    public void setVechicleClass(String vechicleClass) {
        this.vechicleClass = vechicleClass;
    }

    public List<WebScrapVehicleModel> getWebScrapVehicleModels() {
        return webScrapVehicleModels;
    }

    public void setWebScrapVehicleModels(List<WebScrapVehicleModel> webScrapVehicleModels) {
        this.webScrapVehicleModels = webScrapVehicleModels;
    }
}
