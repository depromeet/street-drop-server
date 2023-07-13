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

    public UserResponseDto(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.idfv = user.getIdfv();
    }
}
