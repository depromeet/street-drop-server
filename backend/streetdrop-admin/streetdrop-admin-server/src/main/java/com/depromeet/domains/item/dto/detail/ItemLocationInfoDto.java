package com.depromeet.domains.item.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemLocationInfoDto {
    private double latitude;
    private double longitude;
    private String address;
}
