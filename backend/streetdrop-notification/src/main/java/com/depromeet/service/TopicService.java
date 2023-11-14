package com.depromeet.service;

import com.depromeet.domain.UserDevice;
import com.depromeet.dto.request.TopicSubscribeRequestDto;
import com.depromeet.error.code.FireBaseErrorCode;
import com.depromeet.error.code.TokenErrorCode;
import com.depromeet.error.exceptions.ExternalServerException;
import com.depromeet.error.exceptions.NotFoundException;
import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.UserDeviceRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
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
        } catch (FirebaseMessagingException e) {
            throw new ExternalServerException(FireBaseErrorCode.FIRE_BASE_INTERNAL_SERVER_ERROR);
        }
    }

    public void unsubscribeTopic(TopicSubscribeRequestDto topicSubscribeRequestDto) {
        List<String> tokens = getTokens(topicSubscribeRequestDto.getUserIds());
        try {
            fcmService.subscribeTopicSync(topicSubscribeRequestDto.getTopic(), tokens);
        } catch (FirebaseMessagingException e) {
            throw new ExternalServerException(FireBaseErrorCode.FIRE_BASE_INTERNAL_SERVER_ERROR);
        }
    }

    private List<String> getTokens(List<Long> userIds) {
        return userIds.stream()
                .map(userId -> userDeviceRepository.findByUserId(userId)
                        .orElseThrow(() -> new NotFoundException(TokenErrorCode.TOKEN_NOT_FOUND)))
                .map(UserDevice::getDeviceToken)
                .toList();
    }

}
