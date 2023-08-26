package com.streetdrop.domains.music.genre.repository;

import com.streetdrop.music.genre.Genre;
import com.streetdrop.music.genre.SongGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongGenreRepository extends JpaRepository<SongGenre, Long> {
	Optional<SongGenre> findSongGenreByGenre(Genre genre);
}
