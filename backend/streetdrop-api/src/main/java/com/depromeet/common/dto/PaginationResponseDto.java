package com.depromeet.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaginationResponseDto<T, U extends MetaInterface> {

    private List<T> data;

    private U meta;

}
