package com.depromeet.domain;

import com.depromeet.domain.vo.OsType;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
}