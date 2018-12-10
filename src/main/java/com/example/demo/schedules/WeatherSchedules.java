package com.example.demo.schedules;

import com.example.demo.models.Daily;
import com.example.demo.models.Hourly;
import com.example.demo.repositories.DailyRepository;
import com.example.demo.repositories.HourlyRepository;
import com.example.demo.services.WeatherInfoService;
import net.aksingh.owmjapis.api.APIException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class WeatherSchedules {

	private static final Logger log = LoggerFactory.getLogger(WeatherSchedules.class);

	@Autowired
	private WeatherInfoService weatherInfoService;

	@Autowired
	private HourlyRepository hourlyRepository;

	@Autowired
	private DailyRepository dailyRepository;

	// Every hour
	@Scheduled(cron = "0 0 * * * *")
	public void saveHourlyForecast() throws APIException {

		log.info("{} ------- Saving hourly forecast", new Date());

		Hourly hourly = this.weatherInfoService.getCurrentWeather();

		this.hourlyRepository.save(hourly);

	}

	// Every MidNight
	@Scheduled(cron = "0 0 0 * * *")
	public void saveDailyForecast() {

		log.info("{} ------- Saving daily forecast", new Date());

		DateTime todayAtMidnight = new DateTime().withTimeAtStartOfDay();
		DateTime tomorrowAtMidnight = todayAtMidnight.plusDays(1);

		List<Hourly> hourlies = this.hourlyRepository.getAllBetweenDates(todayAtMidnight.toDate(), tomorrowAtMidnight.toDate());

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);

		Daily daily = this.createDaily(hourlies);
		daily.setDay(calendar.getTime());

		this.dailyRepository.save(daily);

	}

	private Daily createDaily(List<Hourly> hourlies) {

		Daily daily = new Daily();
		Double averageMaxTemp = 0d;
		Double averageMinTemp = 0d;
		Double averagePrecipitation = 0d;

		for(Hourly hourly : hourlies) {

			averageMaxTemp += hourly.getMaxTemperature();
			averageMinTemp += hourly.getMinTemperature();
			averagePrecipitation += hourly.getPrecipitation();

		}

		daily.setMaxTemperature(averageMaxTemp / hourlies.size());
		daily.setMinTemperature(averageMinTemp / hourlies.size());
		daily.setPrecipitation(averagePrecipitation / hourlies.size());

		return daily;

	}

}
