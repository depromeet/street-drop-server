package com.depromeet.controller;

import com.depromeet.dto.request.TopicSubscribeRequestDto;
import com.depromeet.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/subscribe")
    public void subscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        topicService.subscribeTopic(topicSubscribeRequestDto);
    }

    @PostMapping("/unsubscribe")
    public void unsubscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        topicService.unsubscribeTopic(topicSubscribeRequestDto);
    }

}
