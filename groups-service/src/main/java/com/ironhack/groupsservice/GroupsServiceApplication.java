package com.ironhack.groupsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GroupsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupsServiceApplication.class, args);
	}

}
