package com.depromeet.domains.notice.dto.response;

import com.depromeet.announcement.Announcement;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

public record NoticeResponseDto(
        @Schema(description = "공지사항 아이디", example = "1")
        Long noticeId,

        @Schema(description = "공지사항 제목", example = "사용성 개선")
        String title,

        @Schema(description = "공지사항 내용", example = "숨어있던 버그들이 해결되었어요")
        String content,

        @Schema(description = "생성시간", example = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
    public NoticeResponseDto(Announcement announcement) {
        this(
                announcement.getId(),
                announcement.getTitle(),
                announcement.getContent(),
                announcement.getCreatedAt()
        );
    }
}
