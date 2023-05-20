package com.depromeet.streetdrop.domains.music.album.repository;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
	Optional<Album> findAlbumByName(String albumName);
}
