package com.depromeet.domains.item.dto.response;

import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.item.Item;
import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record ItemsResponseDto(List<ItemDetailDto> items) {
        public record ItemDetailDto(
                @Schema(description = "동별 드랍 아이템 개수", example = "1")
                Long itemId,

                @Schema(description = "사용자 정보")
                UserResponseDto user,

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
                @JsonSerialize(using = LocalDateTimeSerializer.class)
                @JsonDeserialize(using =LocalDateTimeDeserializer.class)
                LocalDateTime createdAt,

                @Schema(description = "아이템 좋아요 여부", example = "true")
                boolean isLiked,

                @Schema(description = "아이템 좋아요 개수", example = "100")
                int itemLikeCount
        ) {
                public ItemDetailDto(Item item, Boolean isLiked) {
                        this(
                                item.getId(),
                                new UserResponseDto(item.getUser()),
                                new ItemLocationResponseDto(item.getItemLocation().getName()),
                                new MusicResponseDto(item),
                                item.getContent(),
                                item.getCreatedAt(),
                                isLiked,
                                item.getItemLikeCount()
                        );
                }
        }
}
