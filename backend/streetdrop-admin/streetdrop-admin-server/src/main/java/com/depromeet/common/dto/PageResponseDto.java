package com.depromeet.common.dto;

import java.util.List;

public record PageResponseDto<T>(
        List<T> data,
        PageMetaData meta
) {
}