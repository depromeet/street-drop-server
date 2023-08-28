package com.depromeet.common.error.dto;

import java.util.Optional;

public class ErrorCodeMapper {
    public static Optional<ErrorCode> findByErrorCode(String code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (errorCode.getCode().equals(code)) {
                return Optional.of(errorCode);
            }
        }
        return Optional.empty();
    }
}
