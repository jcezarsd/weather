package com.example.demo.controller;

import com.example.demo.services.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.model.Daily;

@RestController
public class Weather {

	@Autowired
	private WeatherInfoService weatherInfoService;

	@GetMapping("/history/daily/seven")
	@ResponseBody
	public Daily findLastSevenDayHistory() throws ForecastException {

		return weatherInfoService.findLastSevenDayHistory();

	}

}
