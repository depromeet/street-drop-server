package com.depromeet.domains.item.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ItemMusicInfoDto {
    private String title;
    private String artist;
    private String album;
    private String albumImageUrl;
    private String genre;
}
