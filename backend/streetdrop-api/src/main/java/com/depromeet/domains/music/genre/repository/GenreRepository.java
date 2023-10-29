package com.depromeet.domains.music.genre.repository;

import com.depromeet.music.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	List<Genre> findAllByNameIn(List<String> nameList);
}
