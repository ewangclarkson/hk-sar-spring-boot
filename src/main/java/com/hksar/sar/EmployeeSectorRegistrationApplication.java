package com.hksar.sar;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.TimeZone;

@SpringBootApplication
@PropertySource("file:${user.dir}/.env")
public class EmployeeSectorRegistrationApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("Africa/Douala"));
	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSectorRegistrationApplication.class, args);
	}
}
