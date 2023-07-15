package com.depromeet.service;

import com.depromeet.domain.UserDevice;
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
        List<String> tokens = getTokens(topicSubscribeRequestDto.getUserIds());
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (Exception e) {
        }
    }

    public void unsubscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        List<String> tokens = getTokens(topicSubscribeRequestDto.getUserIds());
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (Exception e) {
        }
    }

    private List<String> getTokens(List<Long> userIds) {
        return userIds.stream()
                .map(userId -> userDeviceRepository.findDeviceTokenByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("Token not found for userId: " + userId)))
                .toList();
    }

}
