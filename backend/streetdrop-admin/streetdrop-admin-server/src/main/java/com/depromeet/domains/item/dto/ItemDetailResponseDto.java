package com.depromeet.domains.item.dto;


import com.depromeet.domains.item.dto.detail.ItemBasicInfoDto;
import com.depromeet.domains.item.dto.detail.ItemLocationInfoDto;
import com.depromeet.domains.item.dto.detail.ItemMusicInfoDto;
import com.depromeet.domains.item.dto.detail.ItemUserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class ItemDetailResponseDto {
    private ItemBasicInfoDto basicInfo;
    private ItemUserInfoDto userInfo;
    private ItemMusicInfoDto musicInfo;
    private ItemLocationInfoDto locationInfo;
}
