package com.depromeet.domains.recommend.service;

import com.depromeet.common.error.dto.CommonErrorCode;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.domains.music.service.MusicService;
import com.depromeet.domains.recommend.dto.response.*;
import com.depromeet.domains.recommend.repository.SearchRecommendTermRepository;
import com.depromeet.external.applemusic.service.AppleMusicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

    public void recommendSearchSongs() {
        // 음악 추천 30개
        MusicInfoListResponseDto chartMusicRecommend = appleMusicService.getSongCharts(30);

        // 최근에 드롭된 음악 15개
        MusicInfoListResponseDto recentMusicRecommend = musicService.getRecentMusic(15);

        // 아티스트 추천 10개
        ArtistInfoListResponseDto chartArtistRecommend = appleMusicService.getArtistCharts(10);
    }

}
