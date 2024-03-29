package com.depromeet.domains.notification.controller;

import com.depromeet.domains.notification.dto.request.AllPushRequestDto;
import com.depromeet.domains.notification.dto.request.PushRequestDto;
import com.depromeet.domains.notification.dto.request.TopicPushRequestDto;
import com.depromeet.domains.notification.service.PushService;
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

    @PostMapping("/send")
    public void sendPush(@RequestBody PushRequestDto pushRequestDto) {
        pushService.sendPush(pushRequestDto);
    }

    @PostMapping("/all/send")
    public void sendAllPush(@RequestBody AllPushRequestDto allPushRequestDto) {
        pushService.sendAllPush(allPushRequestDto);
    }

    @PostMapping("/topic/send")
    public void sendTopicPush(@RequestBody TopicPushRequestDto topicPushRequestDto) {
        pushService.sendTopicPush(topicPushRequestDto);
    }

}
