package com.depromeet.domains.weather.response.dto;

public record ResponseDto(
        HeaderDto header,
        BodyDto body
) {
}
