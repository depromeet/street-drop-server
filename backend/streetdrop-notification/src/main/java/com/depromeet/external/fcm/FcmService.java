package com.depromeet.external.fcm;

import com.google.firebase.messaging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FcmService {

    ApnsConfig apnsConfig = ApnsConfig.builder()
            .setAps(Aps.builder().setSound("default").build())
            .build();

    public void sendMessageSync(String token, String content) throws FirebaseMessagingException {
        Message message =
                Message.builder()
                        .setToken(token)
                        .setNotification(Notification.builder().setBody(content).build())
                        .setApnsConfig(apnsConfig)
                        .build();

        FirebaseMessaging.getInstance().send(message);
    }

    public void sendMessageSync(String token, String title, String content) throws FirebaseMessagingException {
        Message message =
                Message.builder()
                        .setToken(token)
                        .setNotification(Notification.builder().setTitle(title).setBody(content).build())
                        .setApnsConfig(apnsConfig)
                        .build();

        FirebaseMessaging.getInstance().send(message);
    }

    public void sendMulticastMessageSync(List<String> tokens, String content) throws FirebaseMessagingException {
        MulticastMessage message =
                MulticastMessage.builder()
                        .addAllTokens(tokens)
                        .setNotification(Notification.builder().setTitle(content).build())
                        .setApnsConfig(apnsConfig)
                        .build();

        FirebaseMessaging.getInstance().sendMulticast(message);
    }

    public void sendMulticastMessageSync(List<String> tokens, String title, String content) throws FirebaseMessagingException {
        MulticastMessage message =
                MulticastMessage.builder()
                        .addAllTokens(tokens)
                        .setNotification(Notification.builder().setTitle(title).setBody(content).build())
                        .setApnsConfig(apnsConfig)
                        .build();

        FirebaseMessaging.getInstance().sendMulticast(message);
    }

    public void sendTopicMessageSync(String topic, String title, String content) throws FirebaseMessagingException {
        Message message =
                Message.builder()
                        .setTopic(topic)
                        .setNotification(Notification.builder().setTitle(title).setBody(content).build())
                        .setApnsConfig(apnsConfig)
                        .build();

        FirebaseMessaging.getInstance().send(message);
    }

    public void sendTopicMessageSync(String topic, String content) throws FirebaseMessagingException {
        Message message =
                Message.builder()
                        .setTopic(topic)
                        .setNotification(Notification.builder().setBody(content).build())
                        .setApnsConfig(apnsConfig)
                        .build();

        FirebaseMessaging.getInstance().send(message);
    }

    public void subscribeTopicSync(String topic, List<String> tokens) throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().subscribeToTopic(tokens, topic);
    }

    public void unsubscribeTopicSync(String topic, List<String> tokens) throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(tokens, topic);
    }
}