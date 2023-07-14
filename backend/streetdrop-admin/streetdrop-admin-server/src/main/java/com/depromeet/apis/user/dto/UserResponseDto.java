package com.depromeet.apis.user.dto;

import com.depromeet.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponseDto {
    private Long id;
    private String nickname;
    private String idfv;
    private String createdAt;
}
