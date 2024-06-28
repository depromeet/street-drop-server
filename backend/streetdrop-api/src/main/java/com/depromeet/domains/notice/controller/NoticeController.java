package com.depromeet.domains.notice.controller;

import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.notice.dto.response.NoticeResponseDto;
import com.depromeet.domains.notice.dto.response.NewNoticeResponseDto;
import com.depromeet.domains.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
@Tag(name = "🔈Notice", description = "Notice API")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 전체 조회")
    @GetMapping
    public ResponseEntity<PaginationResponseDto<?, ?>> getNotices() {
        var response = noticeService.getNotices();
        return ResponseDto.ok(response);
    }

    @Operation(summary = "공지사항 단건 조회")
    @GetMapping("{noticeId}")
    public ResponseEntity<NoticeResponseDto> getNotice(
            @PathVariable(value = "noticeId") Long noticeId
    ) {
        var response = noticeService.getNotice(noticeId);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "신규 공지사항 여부 조회")
    @GetMapping("/new")
    public ResponseEntity<NewNoticeResponseDto> hasNewNotice(
            @Schema(description = "마지막으로 조회한 공지사항 아이디", example = "1")
            @RequestParam(defaultValue = "-1") Long lastNoticeId
    ) {
        var response = noticeService.hasNewNotice(lastNoticeId);
        return ResponseDto.ok(response);
    }

}
