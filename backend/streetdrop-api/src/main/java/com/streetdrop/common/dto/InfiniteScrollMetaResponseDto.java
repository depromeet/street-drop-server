package com.streetdrop.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record InfiniteScrollMetaResponseDto(
        @Schema(description = "전체 데이터 개수", example = "100")
        int totalCount,

        @Schema(description = "다음 페이지 커서", example = "10")
        int nextCursor
) {
}
