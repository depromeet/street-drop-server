package com.depromeet.domains.recommend.search.service;

import com.depromeet.domains.recommend.search.repository.SearchRecommendTermRepository;
import com.depromeet.recommend.search.DescriptionTextVo;
import com.depromeet.recommend.search.SearchRecommendTerm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchRecommendService {
    private final SearchRecommendTermRepository searchRecommendTermRepository;

    public void addRecommendSearchTerm() {
        var title = "검색 추천어";
        var description = List.of(new DescriptionTextVo("검색 추천어 설명", "#000000"));
        var terms = Arrays.asList("검색 추천어1", "검색 추천어2", "검색 추천어3");
        SearchRecommendTerm searchRecommendTerm = new SearchRecommendTerm(title, description, terms);
        searchRecommendTermRepository.save(searchRecommendTerm);
    }
}
