package com.streetdrop.domains.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserProfileResponseDto {
    @Schema(description = "사용자 ID", example = "1")
    private Long userId;
    @Schema(description = "사용자 닉네임", example = "사용자 1")
    private String nickname;
    @Schema(description = "사용자 프로필 이미지 URL")
    private String profileImage;

    public UserProfileResponseDto(Long userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = "";
    }

}