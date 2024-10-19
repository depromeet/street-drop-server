package com.depromeet.domains.recommend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchRecommendResponseDto {
    private List<SearchRecommendCategoryDto> data;
}
