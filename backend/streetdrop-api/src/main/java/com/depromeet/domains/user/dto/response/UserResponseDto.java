package com.depromeet.domains.user.dto.response;

import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDto(
        @Schema(description = "사용자 닉네임", example = "사용자 1")
        String nickname,
        @Schema(description = "사용자 프로필 이미지 URL")
        String profileImage,
        @Schema(description = "사용자 음악앱", example = "youtubemusic")
        String musicApp
) {
    public UserResponseDto(User user) {
        this(
                user.getNickname(),
                "https://s3.orbi.kr/data/file/united/35546557a06831597f6e7851cb6c86e9.jpg",
                "youtubemusic"
        );
    }
}