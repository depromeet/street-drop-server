package com.depromeet.domains.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class IndicatorResponseDto {
    private String title;

    private String value;
}
