package com.depromeet.external.applemusic.service;

import com.depromeet.external.applemusic.dto.response.MusicInfoListResponseDto;
import com.depromeet.external.feign.client.AppleMusicFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppleMusicService {

    private final AppleMusicFeignClient appleMusicFeignClient;

    public MusicInfoListResponseDto getSongCharts() {
        var response = appleMusicFeignClient.getCatalogCharts("songs", 30);
        return MusicInfoListResponseDto.ofAppleMusicResponseDto(response);
    }

}
