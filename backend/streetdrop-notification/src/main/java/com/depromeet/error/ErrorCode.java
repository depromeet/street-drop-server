package com.depromeet.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class ErrorCode implements ErrorCodeInterface {
    private HttpStatus status;
    private ErrorDomain domainCode;
    private String code;
    private String title;
    private String message;

    @Override
    public ErrorCode toErrorCode() {
        return this;
    }
}