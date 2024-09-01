package com.depromeet.domains.search.service;


import com.depromeet.domains.search.response.MusicInfoListResponseDto;

public interface MusicSearchService {
    MusicInfoListResponseDto searchMusic(String keyword);
}



