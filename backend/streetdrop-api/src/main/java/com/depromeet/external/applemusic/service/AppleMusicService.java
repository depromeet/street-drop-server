package com.depromeet.external.applemusic.service;

import com.depromeet.domains.recommend.dto.response.RecommendCategoryDto;
import com.depromeet.external.feign.client.AppleMusicFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleMusicService {

    private final AppleMusicFeignClient appleMusicFeignClient;

    public RecommendCategoryDto getSongCharts(int limit) {
        var response = appleMusicFeignClient.getSongCharts("songs", limit);
        return RecommendCategoryDto.ofAppleMusicResponseDto(response);
    }

    public RecommendCategoryDto getArtistCharts(int limit) {
        var response = appleMusicFeignClient.getAlbumCharts("albums", limit);
        return RecommendCategoryDto.ofAppleMusicResponseDto(response);
    }

}
