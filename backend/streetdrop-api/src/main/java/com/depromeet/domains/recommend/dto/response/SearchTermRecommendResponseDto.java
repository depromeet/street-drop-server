package com.depromeet.domains.recommend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SearchTermRecommendResponseDto {
    private final List<TextColorDto> description;
    private final List<TextColorDto> terms;
}
