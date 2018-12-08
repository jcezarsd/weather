package com.example.demo.services;

import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.model.Daily;

public interface WeatherInfoService {

	Daily findLastSevenDayHistory() throws ForecastException;

}
