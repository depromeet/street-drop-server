package com.depromeet.domains.notification.service;

import com.depromeet.domains.notification.dto.request.AllPushRequestDto;
import com.depromeet.domains.notification.dto.request.PushRequestDto;
import com.depromeet.domains.notification.dto.request.TopicPushRequestDto;
import com.depromeet.common.error.code.FireBaseErrorCode;
import com.depromeet.common.error.exceptions.ExternalServerException;
import com.depromeet.domains.notification.fcm.FcmService;
import com.depromeet.domains.user.repository.UserDeviceRepository;
import com.depromeet.user.UserDevice;
import com.google.firebase.messaging.FirebaseMessagingException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PushService {

    private final UserDeviceRepository userDeviceRepository;
    private final NotificationService notificationService;
    private final FcmService fcmService;

    @Transactional
    public void sendPush(PushRequestDto pushRequestDto) {
        List<String> tokens = pushRequestDto.getUserIds().stream()
                .map(userId -> userDeviceRepository.findByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("Token not found for userId: " + userId)))
                .map(UserDevice::getDeviceToken)
                .toList();

        try {
            if (tokens.size() == 1) {
                fcmService.sendMessageSync(tokens.get(0), pushRequestDto.getContent());
            } else {
                if (pushRequestDto.getTitle() != null) {
                    fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getTitle(), pushRequestDto.getContent());
                } else {
                    fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getContent());
                }
            }
        } catch (FirebaseMessagingException e) {
            throw new ExternalServerException(FireBaseErrorCode.FIRE_BASE_INTERNAL_SERVER_ERROR);
        }

        notificationService.save(pushRequestDto);
    }

    @Transactional
    public void sendAllPush(AllPushRequestDto pushRequestDto) {
        List<String> tokens = userDeviceRepository.findAll()
                .stream()
                .map(UserDevice::getDeviceToken)
                .toList();
        try {
            if (pushRequestDto.getTitle() != null) {
                fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getTitle(), pushRequestDto.getContent());
            } else {
                fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getContent());
            }
        } catch (FirebaseMessagingException e) {
            throw new ExternalServerException(FireBaseErrorCode.FIRE_BASE_INTERNAL_SERVER_ERROR);
        }

        notificationService.save(pushRequestDto);
    }

    @Transactional
    public void sendTopicPush(TopicPushRequestDto tokenPushRequestDto) {
        try {
            if (tokenPushRequestDto.getTitle() != null) {
                fcmService.sendTopicMessageSync(tokenPushRequestDto.getTopic(), tokenPushRequestDto.getTitle(), tokenPushRequestDto.getContent());
            } else {
                fcmService.sendTopicMessageSync(tokenPushRequestDto.getTopic(), tokenPushRequestDto.getContent());
            }
        } catch (FirebaseMessagingException e) {
            throw new ExternalServerException(FireBaseErrorCode.FIRE_BASE_INTERNAL_SERVER_ERROR);
        }

        notificationService.save(tokenPushRequestDto);
    }
}
