package com.streetdrop.controller;

import com.streetdrop.dto.request.AllPushRequestDto;
import com.streetdrop.dto.request.PushRequestDto;
import com.streetdrop.dto.request.TopicPushRequestDto;
import com.streetdrop.service.PushService;
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
