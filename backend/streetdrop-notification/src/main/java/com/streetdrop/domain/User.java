package com.streetdrop.domain;

import lombok.Builder;

@Builder
public class User {

    private Long userId;
    private String deviceToken;

}
