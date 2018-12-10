package com.example.demo.services.impl;

import com.example.demo.Config;
import com.example.demo.services.WeatherInfoService;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Daily;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

	@Override
	public Daily findLastSevenDayHistory() throws ForecastException {

		GeoCoordinates geoCoordinates = new GeoCoordinates(
			new Longitude(Double.valueOf(Config.LOCATION_LNG)),
			new Latitude(Double.valueOf(Config.LOCATION_LAT))
		);

		Daily daily = null;

		for (int i=1; i<=7; i++) {

			ForecastRequest request = new ForecastRequestBuilder()
				.key(new APIKey(Config.DARK_SKY_KEY))
				.time(Instant.now().minus(i, ChronoUnit.DAYS))
				.language(ForecastRequestBuilder.Language.en)
				.units(ForecastRequestBuilder.Units.si)
				.exclude(ForecastRequestBuilder.Block.minutely)
				.exclude(ForecastRequestBuilder.Block.hourly)
				.exclude(ForecastRequestBuilder.Block.currently)
				.location(geoCoordinates).build();

			DarkSkyJacksonClient client = new DarkSkyJacksonClient();
			Forecast forecast = client.forecast(request);

			if(daily == null) {

				daily = forecast.getDaily();

			} else {

				daily.getData().add(forecast.getDaily().getData().get(0));

			}

		}

		return daily;

	}

}
