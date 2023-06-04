package com.depromeet.common.error.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponseDto {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponseDto(ErrorCode errorCode) {
        this.status = errorCode.getStatus().value();
        this.error = errorCode.getStatus().name();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
