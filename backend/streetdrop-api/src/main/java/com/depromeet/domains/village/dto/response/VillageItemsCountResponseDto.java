package com.depromeet.domains.village.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record VillageItemsCountResponseDto(
        @Schema(description = "동별 드랍 아이템 개수", example = "5")
        int numberOfDroppedMusic,

        @Schema(description = "지역 명", example = "서초구 양재2동")
        String villageName
) {
}
