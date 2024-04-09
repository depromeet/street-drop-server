package com.depromeet.user;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.depromeet.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
@Table(name = "users_device")
public class UserDevice extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_device_id")
    private Long id;

    @Column(nullable = false)
    private String deviceToken;

    @Column(nullable = false)
    private boolean alertOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public UserDevice(String deviceToken, User user, boolean alertOn) {
        this.deviceToken = deviceToken;
        this.user = user;
        this.alertOn = alertOn;
    }

    public UserDevice updateDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }
}
