package com.depromeet.domains.announcement.controller;


import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.announcement.dto.request.AnnouncementRequestDto;
import com.depromeet.domains.announcement.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    public ResponseEntity<?> getAnnouncements(
            @PageableDefault(size = 20, page = 0, sort = "id", direction = DESC) Pageable pageable) {
        var response = announcementService.getAnnouncements(pageable);
        return ResponseDto.ok(response);
    }

    @GetMapping("/{announcementId}")
    public ResponseEntity<?> getAnnouncement(@PathVariable Long announcementId) {
        var response = announcementService.getAnnouncement(announcementId);
        return ResponseDto.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createAnnouncement(@RequestBody AnnouncementRequestDto requestDto) {
        var response = announcementService.createAnnouncement(requestDto);
        return ResponseDto.ok(response);
    }


    @DeleteMapping("/{announcementId}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long announcementId) {
        announcementService.deleteAnnouncement(announcementId);
        return ResponseDto.noContent();
    }
}
