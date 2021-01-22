package com.eirlss.helper;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class BookingHelper {
    private static final String COMMA_DELIMITER = ",";

    public boolean isFraudUser(String userName) {
        RestTemplate restTemplate = new RestTemplate();
        boolean isFraudUser = false;
        ResponseEntity<String[]> response = restTemplate.getForEntity("http://localhost:8085/api/v1/fraudUsers", String[].class);
        if (response.hasBody() && !isEmpty(response.getBody())) {
            List<String> fraudUsersList = Arrays.asList(response.getBody());
            isFraudUser = fraudUsersList.stream().anyMatch(fraudUser -> fraudUser.equals(userName));
        }
        return isFraudUser;
    }

}

