package com.depromeet.common.error.code;

import com.depromeet.common.error.ErrorCode;
import com.depromeet.common.error.ErrorCodeInterface;
import com.depromeet.common.error.ErrorDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements ErrorCodeInterface {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request", "This Request BadRequest"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", "Internal Server Error"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid Input Value", "Invalid Input Value"),
    EXTERNAL_SERVER_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "External Sever Error","Invalid Input Value"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "Authentication is required and has failed or has not been provided."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User is not found", "User is not found");

    private final HttpStatus status;
    private final String code = name().toUpperCase();
    private final ErrorDomain domainCode = ErrorDomain.COMMON;
    private final String title;
    private final String message;

    public ErrorCode toErrorCode() {
        return ErrorCode.builder()
                .status(status)
                .domainCode(domainCode)
                .code(code)
                .message(message)
                .build();
    }
}
