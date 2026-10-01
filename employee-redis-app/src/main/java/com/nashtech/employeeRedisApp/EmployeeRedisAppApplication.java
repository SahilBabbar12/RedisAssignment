package com.nashtech.employeeRedisApp;
import org.springframework.cache.annotation.EnableCaching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCaching
public class EmployeeRedisAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRedisAppApplication.class, args);
	}

}
