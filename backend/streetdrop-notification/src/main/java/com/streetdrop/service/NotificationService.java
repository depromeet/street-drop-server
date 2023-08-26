package com.streetdrop.service;

import com.streetdrop.domain.Notification;
import com.streetdrop.domain.Target;
import com.streetdrop.domain.User;
import com.streetdrop.domain.UserDevice;
import com.streetdrop.domain.vo.Channel;
import com.streetdrop.dto.request.AllPushRequestDto;
import com.streetdrop.dto.request.PushRequestDto;
import com.streetdrop.dto.request.TopicPushRequestDto;
import com.streetdrop.repository.NotificationRepository;
import com.streetdrop.repository.UserDeviceRepository;
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
