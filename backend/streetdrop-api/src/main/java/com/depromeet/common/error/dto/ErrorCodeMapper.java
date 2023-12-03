package com.depromeet.common.error.dto;

import com.depromeet.common.error.dto.interfaces.ErrorCode;

import java.util.Optional;

public class ErrorCodeMapper {
    public static Optional<ErrorCode> findByErrorCode(String code) {
        var result = StreetDropErrorCodeList.getInstance().getStreetDropErrorCodeList();
        return result.stream().filter(streetDropErrorCode -> streetDropErrorCode.getErrorResponseCode().equals(code))
                .findFirst().map(StreetDropErrorCode::getErrorCode);
    }

}
