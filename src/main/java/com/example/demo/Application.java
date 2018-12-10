package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySources({@PropertySource("classpath:application.properties")})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
