package com.depromeet.domains.item.error;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ItemErrorCode implements ErrorCodeInterface {
    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "ITEM_NOT_FOUND", "Item Not Found", "Item Not Found"),
    ITEM_ALREADY_LIKED(HttpStatus.CONFLICT, "ITEM_ALREADY_LIKED", "Item Already Liked", "User already item liked"),
    ITEM_ALREADY_REPORTED(HttpStatus.CONFLICT, " ITEM_ALREADY_REPORTED", "Item Already Reported", "User already item reported"),
    ITEM_CREATE_PROCESSING(HttpStatus.PROCESSING, "ITEM_CREATED_PROCESSING", "Item Created Processing", "Another request is currently being processed. Please try again later"),
    ITEM_CREATE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "ITEM_CREATED_FAIL", "Item Created Fail", "Item Created Fail");

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
