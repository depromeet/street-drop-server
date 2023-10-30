package com.depromeet.error.exceptions;


import com.depromeet.error.ErrorCode;
import com.depromeet.error.ErrorCodeInterface;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Exception exception;

    public <T extends ErrorCodeInterface> BusinessException(T errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.toErrorCode();
        this.exception = getException();
    }

}
