package com.depromeet.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class InfiniteScrollMetaResponseDto implements MetaInterface {
        @Schema(description = "전체 데이터 개수", example = "100")
        private int totalCount;

        @Schema(description = "다음 페이지 커서", example = "10")
        private int nextCursor;
}