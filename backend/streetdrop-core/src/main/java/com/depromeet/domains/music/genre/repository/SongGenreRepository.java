package com.depromeet.domains.music.genre.repository;

import com.depromeet.domains.music.genre.entity.SongGenre;
import com.depromeet.domains.music.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongGenreRepository extends JpaRepository<SongGenre, Long> {
	Optional<SongGenre> findSongGenreByGenre(Genre genre);
}
