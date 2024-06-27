package com.depromeet.domains.announcement.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class AnnouncementResponseDto {

    private long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;
}
