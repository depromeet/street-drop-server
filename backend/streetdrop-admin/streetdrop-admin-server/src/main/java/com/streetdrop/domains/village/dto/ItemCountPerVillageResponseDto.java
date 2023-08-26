package com.streetdrop.domains.village.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemCountPerVillageResponseDto {
    private String villageName;
    private Long itemCount;
}
