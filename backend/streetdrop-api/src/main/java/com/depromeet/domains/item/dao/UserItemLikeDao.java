package com.depromeet.domains.item.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class UserItemLikeDao {
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
    private Long userId;
    private String nickname;
}
