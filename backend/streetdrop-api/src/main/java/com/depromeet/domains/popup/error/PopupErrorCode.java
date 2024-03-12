package com.depromeet.domains.popup.error;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PopupErrorCode implements ErrorCodeInterface {
    POPUP_NOT_FOUND(HttpStatus.NOT_FOUND, "POPUP_NOT_FOUND", "Popup Not Found", "Popup Not Found");

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
