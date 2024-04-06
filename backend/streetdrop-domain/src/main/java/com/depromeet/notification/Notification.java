package com.depromeet.notification;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.notification.vo.NotificationType;
import com.depromeet.user.UserDevice;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Notification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 100)
    private String content;

    @Enumerated(STRING)
    @Column(nullable = false)
    private NotificationType notificationType;

    @Column(nullable = false)
    private boolean isViewed;

    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "user_device_id", insertable = false, updatable = false, nullable = false)
    private UserDevice userDevice;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "notification_action_id")
    private NotificationAction notificationAction;

    @Builder
    public Notification(String title, String content, NotificationType notificationType,
                        UserDevice userDevice, NotificationAction notificationAction) {
        this.title = title;
        this.content = content;
        this.notificationType = notificationType;
        this.isViewed = false;
        this.userDevice = userDevice;
        this.notificationAction = notificationAction;
    }

}
