package com.depromeet.domains.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class VersionResponseDto {
    private long id;
    private String version;
    private String count;
    private String percentage;
}
