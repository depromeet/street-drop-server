package com.depromeet.domains.recommend.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.domains.recommend.dto.response.SearchTermRecommendResponseDto;
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

        var description = recommendTerm.getDescription();
        var termList = recommendTerm.getTerms();

        return new SearchTermRecommendResponseDto(description, termList);
    }
}
