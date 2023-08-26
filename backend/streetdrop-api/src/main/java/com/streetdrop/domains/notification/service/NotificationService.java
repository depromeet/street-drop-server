package com.streetdrop.domains.notification.service;

import com.streetdrop.domains.notification.dto.NotificationTokenDto;
import com.streetdrop.user.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificationService {

    @Value(value = "${notification.noti-server-url}")
    private String serverUrl;


    public void saveToken(User user, NotificationTokenDto notificationTokenDto) {

        @Getter
        class NotificationBody {
            private final Long userId;

            private final String token;

            public NotificationBody(User user, NotificationTokenDto notificationTokenDto) {
                this.userId = user.getId();
                this.token = notificationTokenDto.getToken();
            }
        }

        WebClient webClient = WebClient.builder().baseUrl(serverUrl).build();
        NotificationBody notificationBody = new NotificationBody(user, notificationTokenDto);
        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/tokens")
                        .build())
                .bodyValue(notificationBody)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }
}
