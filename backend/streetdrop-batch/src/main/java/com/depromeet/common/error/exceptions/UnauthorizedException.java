package com.depromeet.common.error.exceptions;

import com.depromeet.common.error.ErrorCode;
import com.depromeet.common.error.ErrorCodeInterface;
import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;

@Getter
public class UnauthorizedException extends BadCredentialsException {
    private final ErrorCode errorCode;
    private final Exception exception;

    public <T extends ErrorCodeInterface> UnauthorizedException(T errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode.toErrorCode();
        this.exception = getException();
    }
}
