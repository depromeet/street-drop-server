package com.depromeet.music.artist.repository;

import com.depromeet.music.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Optional<Artist> findArtistByName(String artistName);
}
