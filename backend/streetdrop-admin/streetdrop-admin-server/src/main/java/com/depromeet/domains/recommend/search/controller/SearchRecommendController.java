package com.depromeet.domains.recommend.search.controller;

import com.depromeet.domains.recommend.search.dto.CreateSearchRecommendDto;
import com.depromeet.domains.recommend.search.service.SearchRecommendService;
import com.depromeet.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search-term/recommend")
@RequiredArgsConstructor
public class SearchRecommendController {

    private final SearchRecommendService searchRecommendService;
    @PostMapping
    public ResponseEntity<Integer> addRecommendSearchTerm(
            @RequestBody CreateSearchRecommendDto createSearchRecommendDto
    ) {
        var response = searchRecommendService.createSearchRecommendTerm(createSearchRecommendDto);
        return ResponseDto.created(response);
    }

    @GetMapping
    public ResponseEntity<?> getRecommendSearchTerm(
            @PageableDefault(size = 20, page = 0, sort = "search_recommend_term_id",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = searchRecommendService.getRecommendSearchTerm(pageable);
        return ResponseDto.ok(response);
    }

}
