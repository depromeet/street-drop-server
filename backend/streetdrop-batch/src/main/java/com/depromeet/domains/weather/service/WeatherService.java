package com.depromeet.domains.weather.service;

import com.depromeet.external.feign.client.OpenApiFeignClient;
import com.depromeet.domains.weather.response.dto.WeatherResponseDto;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${open-api.secret-key}")
    private String openApiKey;

    private final OpenApiFeignClient openApiFeignClient;

    public WeatherResponseDto getRealTimeWeather() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"));

        return openApiFeignClient.getRealTimeWeather(openApiKey, 1, 8, "json", date, time, 60, 125);
    }

}
