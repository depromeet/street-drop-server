package com.depromeet.apis.item.dto;

import com.depromeet.apis.common.dto.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemAllResponseDto {
    List<ItemResponseDto> items;
    PageMetaData meta;
}
