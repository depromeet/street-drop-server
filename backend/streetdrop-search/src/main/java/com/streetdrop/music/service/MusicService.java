package com.streetdrop.music.service;

import com.streetdrop.music.dto.response.MusicInfoListResponseDto;


public interface MusicService {
    MusicInfoListResponseDto searchMusic(String keyword);
}



