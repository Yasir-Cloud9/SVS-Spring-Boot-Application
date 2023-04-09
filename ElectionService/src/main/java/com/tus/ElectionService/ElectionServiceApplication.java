package com.tus.ElectionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElectionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectionServiceApplication.class, args);
	}

}
