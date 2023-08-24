package com.depromeet.domains.recommend.controller;

import com.depromeet.common.dto.ResponseDto;
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
@RequestMapping("/search-term/recommend")
@RequiredArgsConstructor
@Tag(name = "SearchRecommend", description = "Search Recommend API")
public class SearchRecommendController {
    private final SearchRecommendService searchRecommendService;

    @Operation(summary = "Recommend Search Term")
    @GetMapping
    public ResponseEntity<SearchTermRecommendResponseDto> recommendSearchTerm() {
        var response = searchRecommendService.recommendSearchTerm();
        return ResponseDto.ok(response);
    }

}
