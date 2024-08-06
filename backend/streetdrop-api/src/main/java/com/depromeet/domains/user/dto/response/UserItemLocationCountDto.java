package com.depromeet.domains.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserItemLocationCountDto (
    @Schema(description = "지역별 드랍 아이템 개수", example = "5")
    Long numberOfDroppedItem,

    @Schema(description = "지역 명", example = "서울 강남구")
    String villageName
) {
    public UserItemLocationCountDto(Long count, String state, String city) {
        this(
                count,
                formatVillageName(state, city)
        );
    }

    private static String formatVillageName(String state, String city) {
        if (state == null) {
            return "";
        }
        if (city == null) {
            return state;
        }
        return state + " " + city;
    }
}
