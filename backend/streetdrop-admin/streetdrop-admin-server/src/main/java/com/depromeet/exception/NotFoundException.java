package com.depromeet.exception;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotFoundException(ErrorCode errorCode, long id) {
        super(errorCode, errorCode.getMessage() + " with id " + id);
    }

    public NotFoundException(ErrorCode errorCode, String content) {
        super(errorCode, content + " is not found");
    }
}