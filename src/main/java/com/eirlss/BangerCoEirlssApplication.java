package com.eirlss;


import com.eirlss.model.FraudUser;
import com.eirlss.repository.FraudUserRepository;
import com.eirlss.service.BookingService;
import com.eirlss.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableScheduling
@ConditionalOnProperty(name = "spring.enable.scheduling")
public class BangerCoEirlssApplication {

	@Autowired
	private BookingService bookingService;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BangerCoEirlssApplication.class, args);
	}
}
