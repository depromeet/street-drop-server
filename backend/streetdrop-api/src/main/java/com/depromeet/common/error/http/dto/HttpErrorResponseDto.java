package com.depromeet.common.error.http.dto;

import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * Represents a Data Transfer Object (DTO) for handling error responses in a standardized way.
 * This class is designed to encapsulate information about an error, including timestamp, trace ID,
 * status code, error response code, title, and a detailed error message.
 * All HTTP errors are processed based on the following error responses
 */
@Getter
@Builder
@AllArgsConstructor
public class HttpErrorResponseDto {

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final String traceId = MDC.get("traceId");

    private final int status;

    private final String errorResponseCode;

    private final String title;

    private final String message;

    public static <T extends ErrorCodeInterface> HttpErrorResponseDto from(T errorCode) {
        return HttpErrorResponseDto.builder()
                .status(errorCode.getStatus().value())
                .errorResponseCode(errorCode.getErrorResponseCode())
                .title(errorCode.getTitle())
                .message(errorCode.getMessage())
                .build();
    }

    public static <T extends ErrorCodeInterface> ResponseEntity<HttpErrorResponseDto> toResponseEntity(T errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(HttpErrorResponseDto.from(errorCode));
    }

}
