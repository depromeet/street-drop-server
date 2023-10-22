package com.depromeet.domains.recommend.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.domains.recommend.dto.response.SearchTermRecommendResponseDto;
import com.depromeet.domains.recommend.dto.response.TextColorDto;
import com.depromeet.domains.recommend.repository.SearchRecommendTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchRecommendService {

    private final SearchRecommendTermRepository searchRecommendTermRepository;

    public SearchTermRecommendResponseDto recommendSearchTerm() {
        var recommendTerm = searchRecommendTermRepository.getRandomSearchRecommendTerm()
                .orElseThrow(() -> new BusinessException(ErrorCode.SEARCH_TERM_NOT_FOUND));

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
}
