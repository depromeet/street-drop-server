package com.depromeet.domains.notification.domain;

import com.depromeet.domains.notification.domain.vo.OsType;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@Document(collection = "user_device")
public class UserDevice {

    @Id
    private String id;
    private Long userId;
    private String deviceToken;
    private OsType osType;
    private String osVersion;
    private boolean isDeleted;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date modifiedAt;

    public UserDevice updateDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
        return this;
    }

}
