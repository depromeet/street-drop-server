package com.depromeet.external.applemusic.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.domains.recommend.dto.response.RecommendCategoryDto;
import com.depromeet.domains.recommend.constant.RecommendType;
import com.depromeet.external.feign.client.AppleMusicFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppleMusicService {

    private final AppleMusicFeignClient appleMusicFeignClient;

    public RecommendCategoryDto getCategoryChart(RecommendType recommendType) {
        if (recommendType == RecommendType.CHART_SONGS) {
            var response = appleMusicFeignClient.getSongCharts("songs", recommendType.getLimit());
            return RecommendCategoryDto.ofAppleMusicResponseDto(recommendType, response);
        }
        else if (recommendType == RecommendType.CHART_ARTIST) {
            var response = appleMusicFeignClient.getAlbumCharts("albums", recommendType.getLimit());
            return RecommendCategoryDto.ofAppleMusicResponseDto(recommendType, response);
        }
        else {
            throw new BusinessException(CommonErrorCode.UNSUPPORTED_TYPE);
        }
    }

}
