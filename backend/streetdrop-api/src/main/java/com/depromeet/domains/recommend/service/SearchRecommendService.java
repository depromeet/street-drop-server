package com.depromeet.domains.recommend.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.domains.music.service.MusicService;
import com.depromeet.domains.recommend.constant.RecommendType;
import com.depromeet.domains.recommend.dto.response.*;
import com.depromeet.domains.recommend.repository.SearchRecommendTermRepository;
import com.depromeet.external.applemusic.service.AppleMusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchRecommendService {

    private final MusicService musicService;
    private final AppleMusicService appleMusicService;
    private final SearchRecommendTermRepository searchRecommendTermRepository;

    public SearchTermRecommendResponseDto recommendSearchTerm() {
        var recommendTerm = searchRecommendTermRepository.getRandomSearchRecommendTerm()
                .orElseThrow(() -> new BusinessException(CommonErrorCode.INTERNAL_SERVER_ERROR));

        var description = recommendTerm.getDescription().stream()
                .map((textColorVo) ->
                        new TextColorDto(textColorVo.getText(), textColorVo.getColor()))
                .toList();

        var termList = recommendTerm.getTerms().stream()
                .map((textColorVo) ->
                        new TextColorDto(textColorVo.getText(), textColorVo.getColor()))
                .toList();

        return new SearchTermRecommendResponseDto(description, termList);
    }

    public RecommendResponseDto recommendSearchSongs() {
        return new RecommendResponseDto(
                List.of(
                        appleMusicService.getCategoryChart(RecommendType.POPULAR_CHART_SONG),
                        musicService.getRecentMusic(RecommendType.RECENT_SONGS),
                        appleMusicService.getCategoryChart(RecommendType.CHART_ARTIST)
                )
        );
    }

}
