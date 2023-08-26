package com.streetdrop.domains.item.dto;

import com.streetdrop.common.dto.PageMetaData;
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
