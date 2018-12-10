package com.example.demo.controller;

import com.example.demo.models.Daily;
import com.example.demo.models.Hourly;
import com.example.demo.services.WeatherInfoService;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Weather {

	@Autowired
	private WeatherInfoService weatherInfoService;

	@GetMapping("/history/daily/seven")
	@ResponseBody
	public List<Daily> findLastSevenDayHistory() {

		return this.weatherInfoService.findLastSevenDayHistory();

	}

	@GetMapping("/weather/current")
	@ResponseBody
	public Hourly getCurrentWeather() throws APIException {

		return this.weatherInfoService.getCurrentWeather();

	}

	@GetMapping("/history/hourly/twentyFour")
	@ResponseBody
	public List<Hourly> getLast24Hours() {

		return this.weatherInfoService.getLast24Hours();

	}

}
