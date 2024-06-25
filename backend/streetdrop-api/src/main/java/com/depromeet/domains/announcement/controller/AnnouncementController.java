package com.depromeet.domains.announcement.controller;

import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.announcement.dto.response.AnnouncementResponseDto;
import com.depromeet.domains.announcement.dto.response.NewAnnouncementResponseDto;
import com.depromeet.domains.announcement.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@Tag(name = "🔈Announcement", description = "Announcement API")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @Operation(summary = "공지사항 전체 조회")
    @GetMapping
    public ResponseEntity<PaginationResponseDto<?, ?>> getAnnouncements() {
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

    @Operation(summary = "신규 공지사항 여부 조회")
    @GetMapping("/new")
    public ResponseEntity<NewAnnouncementResponseDto> hasNewAnnouncement(
            @Schema(description = "마지막으로 조회한 공지사항 아이디", example = "1")
            @RequestParam(defaultValue = "-1") Long lastAnnouncementId
    ) {
        var response = announcementService.hasNewAnnouncement(lastAnnouncementId);
        return ResponseDto.ok(response);
    }

}
