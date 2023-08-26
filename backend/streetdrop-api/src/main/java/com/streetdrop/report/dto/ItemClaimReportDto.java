package com.streetdrop.report.dto;

import lombok.Builder;
import com.streetdrop.item.vo.ItemClaimStatus;

import java.time.LocalDateTime;

public record ItemClaimReportDto(
        Long itemClaimId,
        String itemClaimReason,
        ItemClaimStatus itemClaimStatus,
        Long reportUserId,
        Long itemId,
        String itemContent,
        LocalDateTime claimTime
) {

    @Builder
    public ItemClaimReportDto(Long itemClaimId,
                              String itemClaimReason,
                              ItemClaimStatus itemClaimStatus,
                              Long reportUserId,
                              Long itemId,
                              String itemContent
    ) {
        this(
                itemClaimId,
                itemClaimReason,
                itemClaimStatus,
                reportUserId,
                itemId,
                itemContent,
                LocalDateTime.now()
        );
    }
}
