package com.depromeet.common.error.dto.interfaces;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class ErrorCode implements ErrorCodeInterface {
    private HttpStatus status;
    private String errorResponseCode;
    private String title;
    private String message;

    @Override
    public ErrorCode toErrorCode() {
        return this;
    }

    public void appendMessage(String additionalMessage) {
        this.message += " " + additionalMessage;
    }

}