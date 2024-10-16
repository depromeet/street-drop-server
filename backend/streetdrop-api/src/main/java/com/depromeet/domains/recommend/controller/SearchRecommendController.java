package com.depromeet.domains.recommend.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.recommend.dto.response.SearchRecommendResponseDto;
import com.depromeet.domains.recommend.dto.response.SearchTermRecommendResponseDto;
import com.depromeet.domains.recommend.service.SearchRecommendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "💁Search Recommend", description = "Search Recommend API")
public class SearchRecommendController {

    private final SearchRecommendService searchRecommendService;

    @Operation(summary = "검색어 추천")
    @GetMapping("/search-term/recommend")
    public ResponseEntity<SearchTermRecommendResponseDto> recommendSearchTerm() {
        var response = searchRecommendService.recommendSearchTerm();
        return ResponseDto.ok(response);
    }

    @Operation(summary = "검색어 추천 v2")
    @GetMapping("/v2/search-term/recommend")
    public ResponseEntity<SearchRecommendResponseDto> recommendSearchTerm2() {
        var response = searchRecommendService.recommendSearchSongs();
        return ResponseDto.ok(response);
    }

}
