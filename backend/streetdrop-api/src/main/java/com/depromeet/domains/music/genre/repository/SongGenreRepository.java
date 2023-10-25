package com.depromeet.domains.music.genre.repository;

import com.depromeet.music.genre.SongGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongGenreRepository extends JpaRepository<SongGenre, Long> {
	List<SongGenre> findAllBySongId(Long songId);
}
