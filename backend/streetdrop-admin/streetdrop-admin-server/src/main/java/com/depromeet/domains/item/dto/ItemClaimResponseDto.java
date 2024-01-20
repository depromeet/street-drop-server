package com.depromeet.domains.item.dto;

import com.depromeet.item.vo.ItemClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ItemClaimResponseDto {
    private long itemClaimId;
    private long itemId;
    private String reason;
    private String itemContent;
    private ItemClaimStatus status;
    private LocalDateTime reportAt;
}
