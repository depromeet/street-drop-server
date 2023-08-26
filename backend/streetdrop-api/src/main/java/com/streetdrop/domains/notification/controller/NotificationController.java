package com.streetdrop.domains.notification.controller;

import com.streetdrop.common.dto.ResponseDto;
import com.streetdrop.domains.notification.dto.NotificationTokenDto;
import com.streetdrop.domains.notification.service.NotificationService;
import com.streetdrop.security.annotation.ReqUser;
import com.streetdrop.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Tag(name = "Notification Controller", description = "Notification API")
public class NotificationController {

    private final NotificationService notificationService;

    @Operation(summary = "푸시 토큰 등록")
    @PostMapping("/tokens")
    public ResponseEntity<Void> saveToken(
            @ReqUser User user,
            @Valid @RequestBody NotificationTokenDto notificationTokenDto
    ) {
        notificationService.saveToken(user, notificationTokenDto);
        return ResponseDto.noContent();
    }
}
