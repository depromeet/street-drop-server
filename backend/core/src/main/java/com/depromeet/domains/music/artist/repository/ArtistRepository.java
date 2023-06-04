package com.depromeet.domains.music.artist.repository;

import com.depromeet.domains.music.artist.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Optional<Artist> findArtistByName(String artistName);
}
