package com.depromeet.domains.item.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record  ItemGroupByDateResponseV2Dto(
        @Schema(description = "날짜", example = "2주전")
        String date,
        @Schema(description = "아이템 그룹", example = "아이템 그룹")
        List<ItemGroupResponseV2Dto> value
){}
