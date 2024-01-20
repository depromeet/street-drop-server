package com.depromeet.domains.item.dto;

import com.depromeet.common.dto.PageMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BannedWordAllResponseDto {
    List<BannedWordResponseDto> data;
    PageMetaData meta;
}
