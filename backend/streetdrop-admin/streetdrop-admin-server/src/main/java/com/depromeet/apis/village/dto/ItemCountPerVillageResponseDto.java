package com.depromeet.apis.village.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemCountPerVillageResponseDto {
    private String villageName;
    private Long itemCount;
}