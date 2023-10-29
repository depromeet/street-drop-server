package com.depromeet.domains.music.genre.service;

import com.depromeet.domains.music.genre.repository.SongGenreRepository;
import com.depromeet.music.genre.Genre;
import com.depromeet.music.genre.SongGenre;
import com.depromeet.music.song.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SongGenreService {
    private final SongGenreRepository songGenreRepository;
    private final GenreService genreService;

    @Transactional
    public void createSongGenres(Song song, List<String> genres) {

        List<SongGenre> songGenreList = songGenreRepository.findAllBySongId(song.getId());
        if (!songGenreList.isEmpty()) {
            return;
        }

        List<SongGenre> songGenres = genreService.getGenreList(genres)
                .stream()
                .map(genre -> new SongGenre(song, genre))
                .toList();
        songGenreRepository.saveAll(songGenres);
    }

}
