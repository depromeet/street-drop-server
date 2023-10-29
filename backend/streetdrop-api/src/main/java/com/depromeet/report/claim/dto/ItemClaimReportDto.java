package com.depromeet.report.claim.dto;

import lombok.Builder;
import com.depromeet.item.ItemClaim;
import com.depromeet.item.vo.ItemClaimStatus;

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
