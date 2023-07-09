package com.depromeet.service;

import com.depromeet.dto.request.AllPushRequestDto;
import com.depromeet.dto.request.PushRequestDto;
import com.depromeet.dto.request.TopicPushRequestDto;
import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.TokenRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PushService {

    private final TokenRepository tokenRepository;
    private final FcmService fcmService;

    public void sendPush(PushRequestDto pushRequestDto) {
        List<String> tokens = tokenRepository.findByUserIds(pushRequestDto.getUserIds());
        if (tokens.isEmpty()) {
            throw new RuntimeException("token not found");
        }
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
    }

    public void sendAllPush(AllPushRequestDto pushRequestDto) {
        List<String> tokens = tokenRepository.findAll();
        try {
            if (pushRequestDto.getTitle() != null) {
                fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getTitle(), pushRequestDto.getContent());
            } else {
                fcmService.sendMulticastMessageSync(tokens, pushRequestDto.getContent());
            }
        } catch (FirebaseMessagingException e) {
        }
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
    }
}


