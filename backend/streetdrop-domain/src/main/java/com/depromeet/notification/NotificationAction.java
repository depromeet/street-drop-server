package com.depromeet.notification;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class NotificationAction {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "notification_action_id")
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String pid;

    @Builder
    public NotificationAction(String path, String pid) {
        this.path = path;
        this.pid = pid;
    }
}
