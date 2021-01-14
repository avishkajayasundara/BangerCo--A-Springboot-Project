package com.eirlss.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class ScheduledJobs {
    @Value("${motor.service.url}")
    private String motorServiceUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public ScheduledJobs(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Scheduled(cron="${cron.expression}", zone="Asia/Calcutta")
    private void synchronizeBlacklistedLicenses(){
        ResponseEntity<String[]> response
                = restTemplate.getForEntity(motorServiceUrl+"/blackListedUsers",String[].class);
        if(response.hasBody() && !isEmpty(response.getBody())){
            List<String> blacklistedLicenses = Arrays.asList(response.getBody());
            try  {
                FileWriter csvWriter = new FileWriter("src/asset/banger_blacklisted_licenses.csv");
                csvWriter.append(blacklistedLicenses.stream().collect(Collectors.joining(",")));
                csvWriter.flush();
                csvWriter.close();
            }
            catch (Exception e){
                e.getMessage();
            }
        }
    }
}
