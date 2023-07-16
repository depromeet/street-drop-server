package com.depromeet.domains.item.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ItemGroupByDateResponseDto(
        String date,
        ItemGroupResponseDto value
) {
}
