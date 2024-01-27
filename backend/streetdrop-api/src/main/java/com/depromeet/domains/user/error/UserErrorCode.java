package com.depromeet.domains.user.error;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInterface {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "User Not Found", "User with the corresponding id could not be found"),
    INVALID_USER_EXCEPTION(HttpStatus.FORBIDDEN, "USER_FORBIDDEN", "User does not have permission", "Modify or Delete Not Permitted"),
    USER_NICKNAME_INVALID(HttpStatus.BAD_REQUEST, "USER_NICKNAME_INVALID", "User nickname is invalid", "Nickname must be at least 1 character and not more than 10 characters"),
    USER_CAN_NOT_BLOCK_SELF(HttpStatus.BAD_REQUEST, "USER_CAN_NOT_BLOCK_SELF", "Can Not Block Myself", "You can't block yourself");

    private final HttpStatus status;
    private final String errorResponseCode;
    private final String title;
    private final String message;

    @Override
    public ErrorCode toErrorCode() {
        return ErrorCode
                .builder()
                .status(status)
                .errorResponseCode(errorResponseCode)
                .title(title)
                .message(message)
                .build();
    }
}
