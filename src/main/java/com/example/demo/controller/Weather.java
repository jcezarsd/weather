package com.example.demo.controller;

import com.example.demo.services.WeatherInfoService;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Weather {

	@Autowired
	private WeatherInfoService weatherInfoService;

	@GetMapping("/history/daily/seven")
	@ResponseBody
	public DailyWeatherForecast findLastSevenDayHistory() throws APIException {

		return weatherInfoService.findLastSevenDayHistory();

	}

}
