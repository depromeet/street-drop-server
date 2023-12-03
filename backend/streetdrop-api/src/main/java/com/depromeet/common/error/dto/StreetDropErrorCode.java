package com.depromeet.common.error.dto;

import com.depromeet.common.error.dto.interfaces.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StreetDropErrorCode {

    private String errorResponseCode;

    private ErrorCode errorCode;

}
