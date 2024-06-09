package com.depromeet.domains.announcement.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementsResponseDto;
import com.depromeet.domains.announcement.service.AnnouncementService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@Tag(name = "🔈Announcement", description = "Announcement API")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Operation(summary = "공지사항 전체 조회")
    @GetMapping
    public ResponseEntity<AnnouncementsResponseDto> getAnnouncements() {
        var response = announcementService.getAnnouncements();
        return ResponseDto.ok(response);
    }

    @Operation(summary = "공지사항 단건 조회")
    @GetMapping("{announcementId}")
    public ResponseEntity<AnnouncementResponseDto> getAnnouncement(
            @PathVariable(value = "announcementId") Long announcementId
    ) {
        var response = announcementService.getAnnouncement(announcementId);
        return ResponseDto.ok(response);
    }

}
