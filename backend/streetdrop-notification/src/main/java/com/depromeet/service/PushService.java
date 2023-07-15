package com.depromeet.service;

import com.depromeet.dto.request.AllPushRequestDto;
import com.depromeet.dto.request.PushRequestDto;
import com.depromeet.dto.request.TopicPushRequestDto;
import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.UserDeviceRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushService {

    private final UserDeviceRepository userDeviceRepository;
    private final NotificationService notificationService;
    private final FcmService fcmService;

    public void sendPush(PushRequestDto pushRequestDto) {
        List<String> tokens = pushRequestDto.getUserIds().stream()
                .map(userId -> userDeviceRepository.findDeviceTokenByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("token not found")))
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
        }

        notificationService.save(pushRequestDto);
    }

    public void sendAllPush(AllPushRequestDto pushRequestDto) {
        List<String> tokens = userDeviceRepository.findAllDeviceTokens();
        try {
            if (pushRequestDto.getTitle() != null) {
                fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getTitle(), pushRequestDto.getContent());
            } else {
                fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getContent());
            }
        } catch (FirebaseMessagingException e) {
        }

        notificationService.save(pushRequestDto);
    }

    public void sendTopicPush(TopicPushRequestDto tokenPushRequestDto) {
        try {
            if (tokenPushRequestDto.getTitle() != null) {
                fcmService.sendTopicMessageSync(tokenPushRequestDto.getTopic(), tokenPushRequestDto.getTitle(), tokenPushRequestDto.getContent());
            } else {
                fcmService.sendTopicMessageSync(tokenPushRequestDto.getTopic(), tokenPushRequestDto.getContent());
            }
        } catch (FirebaseMessagingException e) {
        }

        notificationService.save(tokenPushRequestDto);
    }
}


