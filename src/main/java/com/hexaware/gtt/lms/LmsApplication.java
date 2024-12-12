package com.hexaware.gtt.lms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
		
		
	}
	@Bean
	public ModelMapper  modelmappper() {
		return new ModelMapper();
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true); // Allow credentials like Authorization headers
	    config.addAllowedOrigin("http://localhost:3000"); // Allow your frontend
	    config.addAllowedHeader("*"); // Allow all headers
	    config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
	    source.registerCorsConfiguration("/**", config); // Apply this configuration to all endpoints
	    return new CorsFilter(source);
	}

}
