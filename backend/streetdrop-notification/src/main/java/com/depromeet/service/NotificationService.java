package com.depromeet.service;

import com.depromeet.domain.Notification;
import com.depromeet.domain.Target;
import com.depromeet.domain.User;
import com.depromeet.domain.UserDevice;
import com.depromeet.domain.vo.Channel;
import com.depromeet.dto.request.AllPushRequestDto;
import com.depromeet.dto.request.PushRequestDto;
import com.depromeet.dto.request.TopicPushRequestDto;
import com.depromeet.repository.NotificationRepository;
import com.depromeet.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserDeviceRepository userDeviceRepository;

    public void save(PushRequestDto pushRequestDto) {
        for (Long userId : pushRequestDto.getUserIds()) {
            var userDevice = userDeviceRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("token not found"));
            User user = User.builder()
                    .userId(userId)
                    .deviceToken(userDevice.getDeviceToken())
                    .build();

            Target target = Target.builder()
                    .channel(Channel.GENERAL)
                    .build();

            Notification notification = Notification.builder()
                    .user(user)
                    .target(target)
                    .title(pushRequestDto.getTitle())
                    .content(pushRequestDto.getContent())
                    .build();

            notificationRepository.save(notification);
        }
    }

    public void save(AllPushRequestDto pushRequestDto) {
        List<UserDevice> userDevices = userDeviceRepository.findAll();
        for (UserDevice userDevice : userDevices) {
            User user = User.builder()
                    .userId(userDevice.getUserId())
                    .deviceToken(userDevice.getDeviceToken())
                    .build();

            Target target = Target.builder()
                    .channel(Channel.GENERAL)
                    .build();

            Notification notification = Notification.builder()
                    .user(user)
                    .target(target)
                    .title(pushRequestDto.getTitle())
                    .content(pushRequestDto.getContent())
                    .build();

            notificationRepository.save(notification);
        }
    }

    public void save(TopicPushRequestDto tokenPushRequestDto) {
        // TODO: 토픽 저장에 따른 스키마 변경 후 구체화
    }

}
