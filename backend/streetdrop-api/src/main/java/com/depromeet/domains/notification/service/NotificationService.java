package com.depromeet.domains.notification.service;

import com.depromeet.domains.notification.dto.NotificationTokenDto;
import com.depromeet.domains.notification.publisher.SaveTokenPublisher;
import com.depromeet.domains.notification.vo.SaveTokenMessage;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SaveTokenPublisher saveTokenPublisher;

    public void saveToken(User user, NotificationTokenDto notificationTokenDto) {
        saveTokenPublisher.sendMessage(new SaveTokenMessage(user.getId(), notificationTokenDto.getToken()));
    }
}
