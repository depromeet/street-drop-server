package com.depromeet.streetdrop.domains.area.village.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record VillageItemsCountResponseDto(
        @Schema(description = "동별 드랍 아이템 개수", example = "5")
        int numberOfDroppedMusic
) {
}
