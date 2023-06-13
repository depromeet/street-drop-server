package com.depromeet.music.service;

import com.depromeet.music.dto.response.MusicInfoListResponseDto;


public interface MusicService {
    MusicInfoListResponseDto searchMusic(String keyword);
}



