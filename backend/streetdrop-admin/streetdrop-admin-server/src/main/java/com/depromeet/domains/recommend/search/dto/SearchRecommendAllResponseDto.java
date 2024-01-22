package com.depromeet.domains.recommend.search.dto;

import com.depromeet.common.dto.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SearchRecommendAllResponseDto {
    private List<SearchRecommendResponseDto> data;
    private PageMetaData meta;
}
