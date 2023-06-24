package com.depromeet.domains.item.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.service.ItemReportService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items Report", description = "Item Report API")
public class ItemReportController {
    private final ItemReportService itemReportService;

    @Operation(summary = "아이템 신고하기")
    @PostMapping("/{itemId}/report")
    public ResponseEntity<Void> reportItem(
            @ReqUser User user,
            @PathVariable Long itemId
    ) {
        itemReportService.reportItem(user, itemId);
        return ResponseDto.noContent();
    }
}
