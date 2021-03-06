package com.ironhack.sitesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SitesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitesServiceApplication.class, args);
	}

}
