package com.depromeet.dto.request;

import com.depromeet.external.fcm.FcmService;
import com.depromeet.repository.TokenRepository;
import com.depromeet.service.PushRequestDto;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushService {

    private final TokenRepository tokenRepository;
    private final FcmService fcmService;

    public void sendTestPush(PushRequestDto pushRequestDto) {
        String token = tokenRepository.findByUserId(pushRequestDto.getUserId());
        try {
            fcmService.sendMessageSync(token, pushRequestDto.getContent());
        } catch (FirebaseMessagingException e) {
            return;
        }

    }
}
