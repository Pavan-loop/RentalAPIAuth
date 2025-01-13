package com.example.RentalManagementApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class RentalManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalManagementApiApplication.class, args);
		System.out.println("Well Then.... The War Begins Now");
	}

}
