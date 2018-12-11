package com.example.demo.services.impl;

import com.example.demo.Config;
import com.example.demo.models.Daily;
import com.example.demo.models.Hourly;
import com.example.demo.repositories.DailyRepository;
import com.example.demo.repositories.HourlyRepository;
import com.example.demo.services.WeatherInfoService;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class WeatherInfoServiceImpl implements WeatherInfoService {

	@Autowired
	private HourlyRepository hourlyRepository;

	@Autowired
	private DailyRepository dailyRepository;

	private OWM owm = new OWM(Config.API_KEY);

	public WeatherInfoServiceImpl() {

		owm.setUnit(OWM.Unit.METRIC);
		owm.setLanguage(OWM.Language.ENGLISH);

	}

	@Override
	public List<Daily> findLastSevenDayHistory() {

		Date date = new DateTime().minusDays(7).toDate();

		return this.dailyRepository.findLastSevenDays(date);

	}

	@Override
	public Hourly getCurrentWeather() throws APIException {

		CurrentWeather currentWeather = this.owm.currentWeatherByCityName("Tallinn", OWM.Country.ESTONIA);

		Hourly hourly = new Hourly();
		hourly.setHour(new Date());

		if(currentWeather.hasMainData()) {

			hourly.setMaxTemperature(currentWeather.getMainData().getTempMax());
			hourly.setMinTemperature(currentWeather.getMainData().getTempMin());
			hourly.setCurrentTemperature(currentWeather.getMainData().getTemp());

		}

		if(currentWeather.hasRainData()) {

			hourly.setPrecipitation(Objects.requireNonNull(currentWeather.getRainData()).getPrecipVol3h());

		} else {

			hourly.setPrecipitation(0d);

		}

		hourly.setIcon(currentWeather.getWeatherList().get(0).getConditionId().toString());
		hourly.setWeatherName(currentWeather.getWeatherList().get(0).getMainInfo());

		return hourly;

	}

	@Override
	public List<Hourly> getLast24Hours() {

		DateTime now = new DateTime();
		DateTime past = now.minusHours(24);

		return this.hourlyRepository.getAllBetweenDates(past.toDate(), now.toDate());

	}

}
