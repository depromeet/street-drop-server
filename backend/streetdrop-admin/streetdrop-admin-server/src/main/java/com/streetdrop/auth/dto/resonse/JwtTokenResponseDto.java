package com.streetdrop.auth.dto.resonse;

public record JwtTokenResponseDto(String accessToken, String refreshToken) {
}
