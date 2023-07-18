package com.depromeet.report.dto;

import com.depromeet.item.ItemClaimStatus;
import lombok.Builder;

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
