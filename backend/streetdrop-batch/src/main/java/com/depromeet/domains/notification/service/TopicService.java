package com.depromeet.domains.notification.service;

import com.depromeet.domains.notification.dto.request.TopicSubscribeRequestDto;
import com.depromeet.common.error.code.FireBaseErrorCode;
import com.depromeet.common.error.code.TokenErrorCode;
import com.depromeet.common.error.exceptions.ExternalServerException;
import com.depromeet.common.error.exceptions.NotFoundException;
import com.depromeet.domains.notification.fcm.FcmService;
import com.depromeet.domains.notification.repository.UserDeviceRepository;
import com.depromeet.domains.notification.domain.UserDevice;
import com.google.firebase.messaging.FirebaseMessagingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
