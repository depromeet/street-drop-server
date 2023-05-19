package com.depromeet.streetdrop.domains.itemLocation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LocationResponseDto(
        @Schema(description = "사용자 위치", example = "성동구 성수1가 1동")
        String address
) {
}
