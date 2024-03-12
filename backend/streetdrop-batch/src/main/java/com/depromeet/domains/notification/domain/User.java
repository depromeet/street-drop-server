package com.depromeet.domains.notification.domain;

import lombok.Builder;

@Builder
public class User {

    private Long userId;
    private String deviceToken;

}
