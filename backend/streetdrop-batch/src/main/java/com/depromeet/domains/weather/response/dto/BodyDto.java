package com.depromeet.domains.weather.response.dto;

public record BodyDto(
        String dataType,
        ItemsDto items,
        int pageNo,
        int numOfRows,
        int totalCount
) {
}
