package com.depromeet.domains.geo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReverseGeocodeResponseDto(
        @Schema(description = "지역 명", example = "서초구 양재2동")
        String villageName
) {
}
