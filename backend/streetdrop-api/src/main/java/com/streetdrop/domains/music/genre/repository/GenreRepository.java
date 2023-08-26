package com.streetdrop.domains.music.genre.repository;

import com.streetdrop.music.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	Optional<Genre> findGenreByName(String name);
}
