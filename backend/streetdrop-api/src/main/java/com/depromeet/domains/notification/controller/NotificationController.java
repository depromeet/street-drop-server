package com.depromeet.domains.notification.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.notification.dto.NotificationTokenDto;
import com.depromeet.domains.notification.service.NotificationService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
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

    @PostMapping("/tokens")
    public ResponseEntity<Void> createToken(
            @ReqUser User user,
            @Valid @RequestBody NotificationTokenDto notificationTokenDto
    ) {
        notificationService.createToken(user, notificationTokenDto);
        return ResponseDto.noContent();
    }
}
