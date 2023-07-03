package com.depromeet.external.fcm;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FcmService {
    public void sendMessageSync(String token, String content) throws FirebaseMessagingException {
        Message message =
                Message.builder()
                        .setToken(token)
                        .setNotification(Notification.builder().setBody(content).build())
                        .setApnsConfig(
                                ApnsConfig.builder()
                                        .setAps(Aps.builder().setSound("default").build())
                                        .build())
                        .build();

        FirebaseMessaging.getInstance().send(message);
    }
}