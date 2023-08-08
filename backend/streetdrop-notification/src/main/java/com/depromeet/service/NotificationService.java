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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserDeviceRepository userDeviceRepository;

    @Transactional
    public void save(PushRequestDto pushRequestDto) {
        List<Notification> notificationList = new ArrayList<>();

        for (Long userId : pushRequestDto.getUserIds()) {
            var userDevice = userDeviceRepository.findByUserId(userId)
                    .orElseThrow(() -> new RuntimeException("Token not found for userId: " + userId));
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

            notificationList.add(notification);
        }

        notificationRepository.saveAll(notificationList);
    }


    @Transactional
    public void save(AllPushRequestDto pushRequestDto) {
        List<Notification> notificationList = new ArrayList<>();

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

            notificationList.add(notification);
        }

        notificationRepository.saveAll(notificationList);
    }

    @Transactional
    public void save(TopicPushRequestDto tokenPushRequestDto) {
        // TODO: 토픽 저장에 따른 스키마 변경 후 구체화
    }

}
