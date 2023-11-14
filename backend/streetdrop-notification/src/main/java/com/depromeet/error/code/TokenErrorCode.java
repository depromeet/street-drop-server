package com.depromeet.error.code;

import com.depromeet.error.ErrorCode;
import com.depromeet.error.ErrorCodeInterface;
import com.depromeet.error.ErrorDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeInterface {

    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "Token is not found", "Token is not found"),
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
