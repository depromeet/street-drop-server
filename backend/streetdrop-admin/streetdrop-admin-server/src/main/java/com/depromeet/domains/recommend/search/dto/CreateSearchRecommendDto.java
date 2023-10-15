package com.depromeet.domains.recommend.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSearchRecommendDto {
    private String title;
    private List<TextColorDto> description;
    private List<TextColorDto> terms;
}
