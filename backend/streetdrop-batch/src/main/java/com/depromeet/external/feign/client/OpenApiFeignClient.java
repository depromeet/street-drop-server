package com.depromeet.external.feign.client;

import com.depromeet.config.FeignConfig;
import com.depromeet.domains.weather.response.dto.WeatherResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openApiFeignClient", url = "${open-api.base-url}", configuration = FeignConfig.class)
public interface OpenApiFeignClient {

    @GetMapping(value = "${open-api.weather-api-url}", produces = MediaType.APPLICATION_JSON_VALUE)
    WeatherResponseDto getRealTimeWeather(
            @RequestParam("serviceKey") String serviceKey,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("numOfRows") int numOfRows,
            @RequestParam("dataType") String dataType,
            @RequestParam("base_date") String baseDate,
            @RequestParam("base_time") String baseTime,
            @RequestParam("nx") int nx,
            @RequestParam("ny") int ny);

}
