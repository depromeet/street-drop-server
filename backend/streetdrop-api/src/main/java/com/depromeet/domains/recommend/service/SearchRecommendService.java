package com.depromeet.domains.recommend.service;

import com.depromeet.domains.recommend.dto.response.SearchTermRecommendResponseDto;
import com.depromeet.domains.recommend.repository.SearchRecommendTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchRecommendService {

    private final SearchRecommendTermRepository searchRecommendTermRepository;

    public SearchTermRecommendResponseDto recommendSearchTerm() {
        var recommendTerm = searchRecommendTermRepository.getRandomSearchRecommendTerm().orElseThrow(
                () -> new RuntimeException("검색 추천어가 존재하지 않습니다.")
        );

        var description = recommendTerm.getDescription();
        var termList = recommendTerm.getTerms();

        return new SearchTermRecommendResponseDto(description, termList);
    }
}
