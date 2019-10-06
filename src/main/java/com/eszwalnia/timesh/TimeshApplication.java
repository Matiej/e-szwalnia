package com.eszwalnia.timesh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class TimeshApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimeshApplication.class, args);
	}

}
