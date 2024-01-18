package com.depromeet.domains.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegionalAnalysisResponseDto {
    private String name;
    private Long count;
}
