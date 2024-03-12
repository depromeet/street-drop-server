package com.depromeet.common.error;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final ErrorDomain errorDomain;
    private final String code;
    private final String title;
    private final String message;

    public static <T extends ErrorCodeInterface> ResponseEntity<ErrorResponse> toResponseEntity(T errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getStatus().value())
                        .errorDomain(errorCode.getDomainCode())
                        .code(errorCode.getCode())
                        .title(errorCode.getTitle())
                        .message(errorCode.getMessage())
                        .build());
    }
}
