package com.depromeet.common.error.code;

import com.depromeet.common.error.ErrorCode;
import com.depromeet.common.error.ErrorCodeInterface;
import com.depromeet.common.error.ErrorDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FireBaseErrorCode implements ErrorCodeInterface {

    FIRE_BASE_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FireBase Internal Server Error", "FireBase Internal Server Error"),
    TOO_MUCH_REQUEST(HttpStatus.TOO_MANY_REQUESTS, "Too Much Request to FireBase Server", "Too Much Request to FireBase Server, Request After 3 Minutes")
    ;

    private final HttpStatus status;
    private final ErrorDomain domainCode = ErrorDomain.FIREBASE;
    private final String code = name().toUpperCase();
    private final String title;
    private final String message;

    public ErrorCode toErrorCode() {
        return ErrorCode
                .builder()
                .status(status)
                .domainCode(domainCode)
                .code(code)
                .message(message)
                .build();
    }
}
