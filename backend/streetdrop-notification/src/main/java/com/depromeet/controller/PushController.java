package com.depromeet.controller;

import com.depromeet.service.PushRequestDto;
import com.depromeet.dto.request.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/push")
public class PushController {
    private final PushService pushService;

    @PostMapping("/test-send")
    public void sendPush(@RequestBody PushRequestDto pushRequestDto) {
        pushService.sendTestPush(pushRequestDto);
    }
}
