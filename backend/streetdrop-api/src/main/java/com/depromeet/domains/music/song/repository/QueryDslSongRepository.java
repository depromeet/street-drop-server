package com.depromeet.domains.music.song.repository;

import com.depromeet.domains.recommend.dto.response.MusicInfoResponseDto;

import java.util.List;

public interface QueryDslSongRepository {
    List<MusicInfoResponseDto> findRecentSongs(int count);
}
