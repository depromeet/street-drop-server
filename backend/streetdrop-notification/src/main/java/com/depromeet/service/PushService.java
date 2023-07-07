package com.depromeet.service;

import com.depromeet.dto.request.AllPushRequestDto;
import com.depromeet.dto.request.MultiPushRequestDto;
import com.depromeet.dto.request.PushRequestDto;
import com.depromeet.dto.request.TopicPushRequestDto;
import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.TokenRepository;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PushService {

    private final TokenRepository tokenRepository;
    private final FcmService fcmService;

    public void sendPush(PushRequestDto pushRequestDto) {
        Optional<String> token = tokenRepository.findByUserId(pushRequestDto.getUserId());
        if (token.isEmpty()) {
            throw new RuntimeException("token not found");
        }
        try {
            fcmService.sendMessageSync(token.get(), pushRequestDto.getContent());
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

    public void sendMultiPush(MultiPushRequestDto multiPushRequestDto) {
        List<String> tokens = tokenRepository.findByUserIds(multiPushRequestDto.getUserIds());
        try {
            if (multiPushRequestDto.getTitle() != null) {
                fcmService.sendMulticastMessageSync(tokens, multiPushRequestDto.getTitle(), multiPushRequestDto.getContent());
            } else {
                fcmService.sendMulticastMessageSync(tokens, multiPushRequestDto.getContent());
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


