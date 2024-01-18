package com.depromeet.domains.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ReportResponseDto {
    private long id;
    private String title;
    private String content;
    private String status;
}
