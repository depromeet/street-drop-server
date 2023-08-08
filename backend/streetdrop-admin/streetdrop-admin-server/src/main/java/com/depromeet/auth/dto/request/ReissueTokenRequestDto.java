package com.depromeet.auth.dto.request;

import lombok.Getter;

@Getter
public class ReissueTokenRequestDto {
    private String refreshToken;
}
