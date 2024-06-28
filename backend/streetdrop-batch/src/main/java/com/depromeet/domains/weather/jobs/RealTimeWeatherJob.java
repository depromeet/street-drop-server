package com.depromeet.domains.weather.jobs;

import com.depromeet.domains.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealTimeWeatherJob {

	private final WeatherService weatherService;

	public void run() {
		weatherService.getRealTimeWeather();
	}

}
