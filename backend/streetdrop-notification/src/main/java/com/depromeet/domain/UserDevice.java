package com.depromeet.domain;

import com.depromeet.common.domain.BaseTimeEntity;
import com.depromeet.domain.vo.OsType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_device")
public class UserDevice extends BaseTimeEntity {

    @Id
    private Long id;
    private Long userId;
    private String deviceToken;
    private OsType osType;
    private String osVersion;
    private boolean isDeleted;

}