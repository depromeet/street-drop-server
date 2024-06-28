package com.depromeet.domains.weather.scheduler;

import com.depromeet.domains.weather.jobs.RealTimeWeatherJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RealTimeWeatherBatchScheduler {

	private final RealTimeWeatherJob realTimeWeatherJob;

	@Scheduled(cron = "0 0 */3 * * *")
	public void runWeatherJob() {
		realTimeWeatherJob.run();
	}

}
