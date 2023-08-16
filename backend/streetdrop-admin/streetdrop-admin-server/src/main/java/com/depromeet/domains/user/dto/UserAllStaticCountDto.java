package com.depromeet.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAllStaticCountDto {
    private Long allUserCount;
    private int todayUserCount;
    private int dropUserCount;
}
