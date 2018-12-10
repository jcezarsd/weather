package com.example.demo.services;

import com.example.demo.models.Hourly;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.DailyWeatherForecast;

public interface WeatherInfoService {

	DailyWeatherForecast findLastSevenDayHistory() throws APIException;

	Hourly getCurrentWeather() throws APIException;
}
