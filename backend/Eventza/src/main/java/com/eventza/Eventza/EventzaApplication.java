package com.eventza.Eventza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EventzaApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventzaApplication.class, args);
	}
}
