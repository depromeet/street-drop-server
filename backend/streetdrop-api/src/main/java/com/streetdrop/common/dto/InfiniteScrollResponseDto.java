package com.streetdrop.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InfiniteScrollResponseDto<T, U> {

    private List<T> data;

    private U meta;

}
