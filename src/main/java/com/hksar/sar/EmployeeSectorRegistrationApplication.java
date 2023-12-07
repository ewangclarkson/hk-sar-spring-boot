package com.hksar.sar;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class EmployeeSectorRegistrationApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Africa/Douala"));
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSectorRegistrationApplication.class, args);
	}
}
