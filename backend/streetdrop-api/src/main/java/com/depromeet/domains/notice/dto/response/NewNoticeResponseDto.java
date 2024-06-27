package com.depromeet.domains.notice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record NewNoticeResponseDto(
        @Schema(description = "새로운 공지사항이 있는지 여부", example = "true")
        boolean hasNewNotice
){
    public NewNoticeResponseDto(boolean hasNewNotice) {
        this.hasNewNotice = hasNewNotice;
    }
}
