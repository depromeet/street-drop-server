package com.depromeet.report.dto;

import com.depromeet.item.ItemClaim;
import com.depromeet.item.ItemClaimStatus;

import java.time.LocalDateTime;

public record ItemClaimReportDto(
        Long itemClaimId,
        String itemClaimReason,
        ItemClaimStatus itemClaimStatus,
        String reporter,
        Long itemId,
        String itemContent,
        LocalDateTime claimTime
) {
    public ItemClaimReportDto(ItemClaim itemClaim) {
        this(
                itemClaim.getId(),
                itemClaim.getReason(),
                itemClaim.getStatus(),
                itemClaim.getUser().getNickname(),
                itemClaim.getItem().getId(),
                itemClaim.getItem().getContent(),
                itemClaim.getCreatedAt()
        );
    }
}
