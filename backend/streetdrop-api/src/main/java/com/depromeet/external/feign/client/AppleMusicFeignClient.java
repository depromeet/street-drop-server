package com.depromeet.external.feign.client;

import com.depromeet.external.applemusic.config.AppleMusicAuthConfig;
import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicAlbumChartResponseDto;
import com.depromeet.external.applemusic.dto.response.catalogchart.AppleMusicSongChartResponseDto;
import com.depromeet.external.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openApiFeignClient", url = "${apple.music.api.base-url}",
        configuration = {FeignConfig.class, AppleMusicAuthConfig.class})
public interface AppleMusicFeignClient {
    @GetMapping(value = "${apple.music.api.get-catalog-charts-url}", produces = MediaType.APPLICATION_JSON_VALUE)
    AppleMusicSongChartResponseDto getSongCharts(
            @RequestParam("types") String types,
            @RequestParam("limit") int limit
    );

    @GetMapping(value = "${apple.music.api.get-catalog-charts-url}", produces = MediaType.APPLICATION_JSON_VALUE)
    AppleMusicAlbumChartResponseDto getAlbumCharts(
            @RequestParam("types") String types,
            @RequestParam("limit") int limit
    );
}
