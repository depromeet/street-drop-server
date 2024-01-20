package com.depromeet.domains.item.dto;

import com.depromeet.item.vo.ItemClaimStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemClaimRequestDto {
    private Long itemClaimId;
    private ItemClaimStatus itemClaimStatus;
}
