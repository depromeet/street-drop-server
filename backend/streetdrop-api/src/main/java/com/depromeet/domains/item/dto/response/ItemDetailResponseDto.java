package com.depromeet.domains.item.dto.response;

import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.depromeet.domains.user.dto.response.UserProfileResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

public record ItemDetailResponseDto(
        @Schema(description = "아이템 아이디", example = "1")
        Long itemId,
        
        @Schema(description = "사용자 정보")
        UserProfileResponseDto user,

        @Schema(description = "사용자 위치", example = "성동구 성수1가 1동")
        ItemLocationResponseDto location,

        @Schema(description = "음악 정보")
        MusicResponseDto music,

        @Schema(description = "사용자 코멘트", example = "이 노래 좋아요")
        String content,

        @Schema(description = "생성시간", example = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd HH:mm:ss",
                locale = "Asia/Seoul"
        )
        LocalDateTime createdAt,

        @Schema(description = "아이템 좋아요 여부", example = "true")
        boolean isLiked,

        @Schema(description = "아이템 좋아요 개수", example = "100")
        int itemLikeCount

) {
    @Builder
    public ItemDetailResponseDto(
            Long itemId,
            UserProfileResponseDto user,
            ItemLocationResponseDto location,
            MusicResponseDto music,
            String content,
            LocalDateTime createdAt,
            boolean isLiked,
            int itemLikeCount
    ) {
        this.itemId = itemId;
        this.user = user;
        this.location = location;
        this.music = music;
        this.content = content;
        this.createdAt = createdAt;
        this.isLiked = isLiked;
        this.itemLikeCount = itemLikeCount;
    }

}



