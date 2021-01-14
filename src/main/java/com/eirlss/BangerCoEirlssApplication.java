package com.eirlss;

import com.eirlss.model.VehicleType;
import com.eirlss.repository.VehicleTypeRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@ConditionalOnProperty(name = "spring.enable.scheduling")
public class BangerCoEirlssApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BangerCoEirlssApplication.class, args);


	}
}
