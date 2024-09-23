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
        return switch (recommendType) {
            case POPULAR_CHART_SONG -> {
                var response = appleMusicFeignClient.getSongCharts("songs", recommendType.getLimit());
                yield RecommendCategoryDto.ofAppleMusicResponseDto(recommendType, response);
            }
            case CHART_ARTIST -> {
                var response = appleMusicFeignClient.getAlbumCharts("albums", recommendType.getLimit());
                yield RecommendCategoryDto.ofAppleMusicResponseDto(recommendType, response);
            }
            default -> throw new BusinessException(CommonErrorCode.UNSUPPORTED_TYPE);
        };
    }

}
