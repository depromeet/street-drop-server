package com.depromeet.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserNicknameCreateRequestDto {

    private String preNickname;

    private String postNickname;
}
