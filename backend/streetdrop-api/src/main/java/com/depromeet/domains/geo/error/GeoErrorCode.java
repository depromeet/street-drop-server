package com.depromeet.domains.geo.error;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import com.depromeet.common.error.dto.interfaces.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeoErrorCode implements ErrorCodeInterface {
    NOT_SUPPORT_LOCATION(HttpStatus.BAD_REQUEST, "GEO_NOT_SUPPORT_LOCATION", "Not Support Location", "The Location is currently not serviced");

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
