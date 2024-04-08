package com.depromeet.domains.notification.service;

import static com.depromeet.common.error.code.TokenErrorCode.TOKEN_NOT_FOUND;

import com.depromeet.common.error.exceptions.NotFoundException;
import com.depromeet.domains.notification.dto.request.AllPushRequestDto;
import com.depromeet.domains.notification.dto.request.PushRequestDto;
import com.depromeet.domains.notification.dto.request.TopicPushRequestDto;
import com.depromeet.domains.notification.repository.NotificationRepository;
import com.depromeet.domains.user.repository.UserDeviceRepository;
import com.depromeet.notification.Notification;
import com.depromeet.notification.NotificationAction;
import com.depromeet.notification.vo.NotificationType;
import com.depromeet.user.UserDevice;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                    .orElseThrow(() -> new NotFoundException(TOKEN_NOT_FOUND));
            if (userDevice.isAlertOn()) {
                var notificationAction = NotificationAction.builder()
                        .path(pushRequestDto.getPath())
                        .pid(pushRequestDto.getPid())
                        .build();
                var notification = Notification.builder()
                        .title(pushRequestDto.getTitle())
                        .content(pushRequestDto.getContent())
                        .notificationType(pushRequestDto.getNotificationType())
                        .userDevice(userDevice)
                        .notificationAction(notificationAction)
                        .build();
                notificationList.add(notification);
            }
        }

        notificationRepository.saveAll(notificationList);
    }


    @Transactional
    public void save(AllPushRequestDto pushRequestDto) {
        List<Notification> notificationList = new ArrayList<>();

        var userDevices = userDeviceRepository.findAll();
        var notificationAction = NotificationAction.builder()
                .path(pushRequestDto.getPath())
                .pid(pushRequestDto.getPid())
                .build();

        for (UserDevice userDevice : userDevices) {
            if (userDevice.isAlertOn()) {
                var notification = Notification.builder()
                        .title(pushRequestDto.getTitle())
                        .content(pushRequestDto.getContent())
                        .notificationType(NotificationType.ALL)
                        .userDevice(userDevice)
                        .notificationAction(notificationAction)
                        .build();
                notificationList.add(notification);
            }
        }

        notificationRepository.saveAll(notificationList);
    }

    @Transactional
    public void save(TopicPushRequestDto tokenPushRequestDto) {
        // TODO: 토픽 저장에 따른 스키마 변경 후 구체화
    }

}
