package com.depromeet.domains.user.dto.response;

import java.util.List;

public record UserItemCountGroupByLocationDto(
        List<UserItemLocationCountDto> data
) {
}
