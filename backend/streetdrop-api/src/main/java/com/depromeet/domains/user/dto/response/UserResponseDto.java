package com.depromeet.domains.user.dto.response;

import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponseDto(
        @Schema(description = "사용자 ID", example = "1")
        Long userId,
        @Schema(description = "사용자 닉네임", example = "사용자 1")
        String nickname,
        @Schema(description = "사용자 프로필 이미지 URL")
        String profileImage,
        @Schema(description = "사용자 음악앱", example = "[youtubemusic, spotify, applemusic]")
        String musicApp
) {
    public UserResponseDto(User user) {
        this(
                user.getId(),
                user.getNickname(),
                "",
                user.getMusicApp().getAppName() != null ? user.getMusicApp().getAppName() : MusicApp.YOUTUBE_MUSIC.getAppName()
        );
    }

    public UserResponseDto(Long userId, String nickname) {
        this(userId, nickname, "", MusicApp.YOUTUBE_MUSIC.getAppName());
    }
}
