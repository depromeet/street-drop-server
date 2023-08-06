package com.depromeet.domains.item.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ItemDao {
    private int weekAgo;
    private Long itemId;
    private String content;
    private LocalDateTime createdAt;
    private String locationName;
    private String songName;
    private String albumName;
    private String artistName;
    private String albumThumbnail;
    private Long itemCount;
}
