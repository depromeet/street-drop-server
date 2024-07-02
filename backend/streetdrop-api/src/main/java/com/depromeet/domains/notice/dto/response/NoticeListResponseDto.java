package com.depromeet.domains.notice.dto.response;

import com.depromeet.notice.Notice;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record NoticeListResponseDto(
        @Schema(description = "공지사항 아이디", example = "1")
        Long noticeId,

        @Schema(description = "공지사항 제목", example = "사용성 개선")
        String title,

        @Schema(description = "생성시간", example = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
    public NoticeListResponseDto(Notice notice) {
        this(
                notice.getId(),
                notice.getTitle(),
                notice.getCreatedAt()
        );
    }
}
