package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class Config {

	private Environment env;
	public static String DARK_SKY_KEY;
	public static String LOCATION_LAT;
	public static String LOCATION_LNG;

	@Autowired
	public Config(Environment env) {

		this.env = env;

		DARK_SKY_KEY = env.getProperty("api.darksky.key");
		LOCATION_LAT = env.getProperty("location.lat");
		LOCATION_LNG = env.getProperty("location.lng");

	}

}
