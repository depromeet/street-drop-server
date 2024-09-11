package com.depromeet.domains.recommend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecommendResponseDto {
    private List<RecommendCategoryDto> data;
}
