package com.depromeet.domains.item.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ItemBasicInfoDto {
    private Long id;
    private int likeCount;
    private LocalDateTime createdAt;
}
