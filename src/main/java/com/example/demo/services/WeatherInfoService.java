package com.example.demo.services;

import com.example.demo.models.Daily;
import com.example.demo.models.Hourly;
import net.aksingh.owmjapis.api.APIException;

import java.util.List;

public interface WeatherInfoService {

	List<Daily> findLastSevenDayHistory();

	Hourly getCurrentWeather() throws APIException;

	List<Hourly> getLast24Hours();
}
