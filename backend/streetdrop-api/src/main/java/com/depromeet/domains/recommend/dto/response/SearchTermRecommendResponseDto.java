package com.depromeet.domains.recommend.dto.response;


import com.depromeet.recommend.search.DescriptionTextVo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SearchTermRecommendResponseDto {
    private final List<DescriptionTextVo> description;
    private final List<String> terms;
}
