package com.depromeet.external.applemusic.service;

import com.depromeet.domains.recommend.dto.response.RecommendCategoryDto;
import com.depromeet.domains.recommend.constant.RecommendType;
import com.depromeet.external.feign.client.AppleMusicFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleMusicService {

    private final AppleMusicFeignClient appleMusicFeignClient;

    public RecommendCategoryDto getSongCharts(RecommendType recommendType) {
        var response = appleMusicFeignClient.getSongCharts("songs", recommendType.getLimit());
        return RecommendCategoryDto.ofAppleMusicResponseDto(recommendType, response);
    }

    public RecommendCategoryDto getArtistCharts(RecommendType recommendType) {
        var response = appleMusicFeignClient.getAlbumCharts("albums", recommendType.getLimit());
        return RecommendCategoryDto.ofAppleMusicResponseDto(recommendType, response);
    }

}
