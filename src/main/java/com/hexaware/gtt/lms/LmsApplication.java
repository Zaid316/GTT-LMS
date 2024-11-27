package com.hexaware.gtt.lms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}
	@Bean
	public ModelMapper  modelmappper() {
		return new ModelMapper();
	}

}
