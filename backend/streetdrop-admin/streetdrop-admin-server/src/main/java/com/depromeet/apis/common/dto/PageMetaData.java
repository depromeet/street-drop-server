package com.depromeet.apis.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PageMetaData {
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
