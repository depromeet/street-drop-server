package com.depromeet.common.error.exception.external;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.Getter;

@Getter
public class ExternalServerException extends RuntimeException {
    private final Exception e;
    private final ErrorCode errorCode;

    public <T extends ErrorCodeInterface> ExternalServerException(Exception e, T errorCode) {
        super(errorCode.getMessage());
        this.e = e;
        this.errorCode = errorCode.toErrorCode();
    }

    public <T extends ErrorCodeInterface> ExternalServerException(T errorCode, String message, Exception e) {
        super(message);
        this.errorCode = errorCode.toErrorCode();
        this.e = e;
    }
}
