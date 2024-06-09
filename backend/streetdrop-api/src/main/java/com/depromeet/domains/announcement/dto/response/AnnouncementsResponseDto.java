package com.depromeet.domains.announcement.dto.response;

import java.util.List;

public record AnnouncementsResponseDto(
        List<AnnouncementResponseDto> data
) {
}
