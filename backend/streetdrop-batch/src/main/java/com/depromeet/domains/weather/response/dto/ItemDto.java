package com.depromeet.domains.weather.response.dto;

public record ItemDto(
        String baseDate,
        String baseTime,
        String category,
        int nx,
        int ny,
        String obsrValue
) {
}
