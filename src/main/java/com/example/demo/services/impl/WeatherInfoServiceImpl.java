package com.example.demo.services.impl;

import com.example.demo.Config;
import com.example.demo.models.Hourly;
import com.example.demo.repositories.HourlyRepository;
import com.example.demo.services.WeatherInfoService;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

	@Autowired
	private HourlyRepository hourlyRepository;

	private OWM owm = new OWM(Config.API_KEY);
	private Double tallinLat = Double.valueOf(Config.LOCATION_LAT);
	private Double tallinLng = Double.valueOf(Config.LOCATION_LNG);

	@Override
	public DailyWeatherForecast findLastSevenDayHistory() throws APIException {

		return this.owm.dailyWeatherForecastByCoords(this.tallinLat, this.tallinLng, 7);

	}

	@Override
	public Hourly getCurrentWeather() throws APIException {

		CurrentWeather currentWeather = this.owm.currentWeatherByCoords(this.tallinLat, this.tallinLng);

		Hourly hourly = new Hourly();
		hourly.setHour(currentWeather.getDateTime());
		hourly.setMaxTemperature(Objects.requireNonNull(currentWeather.getMainData()).getTempMax());
		hourly.setMinTemperature(currentWeather.getMainData().getTempMin());
		hourly.setPrecipitation(Objects.requireNonNull(currentWeather.getRainData()).getPrecipVol3h());

		this.hourlyRepository.save(hourly);

		return hourly;

	}

}
