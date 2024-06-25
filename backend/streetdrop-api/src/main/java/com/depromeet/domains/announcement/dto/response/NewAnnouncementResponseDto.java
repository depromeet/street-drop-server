package com.depromeet.domains.announcement.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record NewAnnouncementResponseDto (
        @Schema(description = "새로운 공지사항이 있는지 여부", example = "true")
        boolean hasNewAnnouncement
){
    public NewAnnouncementResponseDto(boolean hasNewAnnouncement) {
        this.hasNewAnnouncement = hasNewAnnouncement;
    }
}
