package com.depromeet.notification;

import lombok.Builder;

@Builder
public class User {

    private Long userId;
    private String deviceToken;

}
