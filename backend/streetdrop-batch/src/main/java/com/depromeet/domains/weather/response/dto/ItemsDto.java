package com.depromeet.domains.weather.response.dto;

import java.util.List;

public record ItemsDto(
        List<ItemDto> item
) {
}
