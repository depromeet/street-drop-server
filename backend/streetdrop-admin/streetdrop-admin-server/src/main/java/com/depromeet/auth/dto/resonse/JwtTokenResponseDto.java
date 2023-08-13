package com.depromeet.auth.dto.resonse;

public record JwtTokenResponseDto(String accessToken, String refreshToken) {
}
