package com.depromeet.domains.recommend.dto.response;


import com.depromeet.recommend.search.TextColorVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SearchTermRecommendResponseDto {
    private final List<TextColorVo> description;
    private final List<TextColorVo> terms;
}
