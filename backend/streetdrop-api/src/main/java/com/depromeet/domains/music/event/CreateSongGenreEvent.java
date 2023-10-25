package com.depromeet.domains.music.event;

import com.depromeet.music.song.Song;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CreateSongGenreEvent {
    public List<String> genreNameList;
    public Song song;
}
