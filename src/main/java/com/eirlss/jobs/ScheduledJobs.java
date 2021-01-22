package com.eirlss.jobs;

import com.eirlss.model.FlaggedLicenceHolder;
import com.eirlss.repository.FlaggedLicenceHolderRepository;
import com.eirlss.service.impl.BookingServiceImpl;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class ScheduledJobs {

    @Value("${motor.service.url}")
    private final String motorServiceUrl = "http://localhost:8085/api/v1/blackListedUsers";

    @Autowired
    private FlaggedLicenceHolderRepository flaggedLicenceHolderRepository;

    @Autowired
    public ScheduledJobs ScheduledJobs;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;


    @Scheduled(cron = "0 1 0 * * *", zone = "Asia/Calcutta")
    public void synchronizeBlacklistedLicenses() {
        RestTemplate restTemplate3 = new RestTemplate();
        Type founderListType = new TypeToken<ArrayList<FlaggedLicenceHolder>>(){}.getType();
        Gson gson = new Gson();
        ResponseEntity<String> response
                = restTemplate3.getForEntity(motorServiceUrl, String.class);
        List<FlaggedLicenceHolder> users = gson.fromJson(response.getBody(),founderListType);
        bookingServiceImpl.syncBlacklistedUsers(users);
    }
}
