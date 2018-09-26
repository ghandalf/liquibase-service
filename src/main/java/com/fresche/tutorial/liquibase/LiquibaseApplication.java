package com.fresche.tutorial.liquibase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiquibaseApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(LiquibaseApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Building the context and start the application.");
		SpringApplication.run(LiquibaseApplication.class, args);		
	}
	
}
