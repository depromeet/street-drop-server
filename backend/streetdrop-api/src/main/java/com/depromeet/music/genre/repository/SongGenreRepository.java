package com.depromeet.music.genre.repository;

import com.depromeet.music.genre.Genre;
import com.depromeet.music.genre.SongGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongGenreRepository extends JpaRepository<SongGenre, Long> {
	Optional<SongGenre> findSongGenreByGenre(Genre genre);
}
