package com.depromeet.service;

import com.depromeet.dto.request.TopicSubscribeRequestDto;
import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TokenRepository tokenRepository;
    private final FcmService fcmService;

    public void subscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        List<String> tokens = tokenRepository.findByUserIds(topicSubscribeRequestDto.getUserIds());
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (Exception e) {
        }
    }

    public void unsubscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        List<String> tokens = tokenRepository.findByUserIds(topicSubscribeRequestDto.getUserIds());
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (Exception e) {
        }
    }

}
