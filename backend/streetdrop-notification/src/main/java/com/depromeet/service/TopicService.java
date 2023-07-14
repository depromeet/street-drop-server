package com.depromeet.service;

import com.depromeet.dto.request.TopicSubscribeRequestDto;
import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final UserDeviceRepository userDeviceRepository;
    private final FcmService fcmService;

    public void subscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        List<String> tokens = topicSubscribeRequestDto.getUserIds().stream()
                .map(userId -> userDeviceRepository.findDeviceTokenByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("token not found")))
                .toList();
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (Exception e) {
        }
    }

    public void unsubscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        List<String> tokens = topicSubscribeRequestDto.getUserIds().stream()
                .map(userId -> userDeviceRepository.findDeviceTokenByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("token not found")))
                .toList();
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (Exception e) {
        }
    }

}
