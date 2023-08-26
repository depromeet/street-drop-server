package com.streetdrop.domains.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDetailInfoResponseDto {
    private int allDropCount;
    private String mainDropLocation;
    private String dropLocations;
    private String interestGenre;
}
