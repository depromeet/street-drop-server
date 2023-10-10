package com.depromeet.domains.recommend.search.service;

import com.depromeet.domains.recommend.search.dto.CreateSearchRecommendDto;
import com.depromeet.domains.recommend.search.repository.SearchRecommendTermRepository;
import com.depromeet.recommend.search.SearchRecommendTerm;
import com.depromeet.recommend.search.TextColorVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SearchRecommendService {
    private final SearchRecommendTermRepository searchRecommendTermRepository;

    @Transactional
    public int createSearchRecommendTerm(CreateSearchRecommendDto createSearchRecommendDto) {
        var title = createSearchRecommendDto.getTitle();
        var description = createSearchRecommendDto.getDescription().stream()
                .map(textColorDto -> new TextColorVo(textColorDto.getText(), textColorDto.getColor()))
                .toList();
        var terms = createSearchRecommendDto.getTerms().stream()
                .map(textColorDto -> new TextColorVo(textColorDto.getText(), textColorDto.getColor()))
                .toList();
        var searchRecommendTerm = SearchRecommendTerm.builder().title(title).description(description).terms(terms).build();
        var result = searchRecommendTermRepository.save(searchRecommendTerm);
        return result.getId().intValue();
    }
}
