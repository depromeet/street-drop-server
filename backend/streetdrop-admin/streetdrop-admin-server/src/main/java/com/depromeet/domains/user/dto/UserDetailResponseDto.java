package com.depromeet.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDetailResponseDto {
    private UserBasicInfoResponseDto userBasicInfo;
    private UserDetailInfoResponseDto userDetailInfo;
    private List<UserActivityResponseDto> userActivity;
}
