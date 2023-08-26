package com.streetdrop.domains.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class ItemResponseDto {
    private Long id;
    private String songName;
    private String artistName;
    private String dropLocationName;
    private String userNickname;
    private String comment;
    private LocalDateTime createdAt;
}
