package com.dr7.dr7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Dr7Application {

	public static void main(String[] args) {
		SpringApplication.run(Dr7Application.class, args);
	}

}
