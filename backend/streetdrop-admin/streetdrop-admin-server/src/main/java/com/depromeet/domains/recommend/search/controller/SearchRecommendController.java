package com.depromeet.domains.recommend.search.controller;

import com.depromeet.domains.recommend.search.dto.CreateSearchRecommendDto;
import com.depromeet.domains.recommend.search.service.SearchRecommendService;
import com.depromeet.domains.user.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}