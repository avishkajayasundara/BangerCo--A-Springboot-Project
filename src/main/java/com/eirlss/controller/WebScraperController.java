package com.eirlss.controller;

import com.eirlss.dto.WebScrapVehicleDetails;
import com.eirlss.service.WebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scrapData")
@CrossOrigin(origins = "http://localhost:3000")
public class WebScraperController {
    private final WebScraperService webScraperService;

    @Autowired
    public WebScraperController(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public WebScrapVehicleDetails webScrapedData(){
        return webScraperService.getWebScrapedData();
    }
}
