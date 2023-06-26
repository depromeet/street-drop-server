package com.depromeet.domains.music.album.repository;

import com.depromeet.music.album.AlbumCover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumCoverRepository extends JpaRepository<AlbumCover, Long> {
	Optional<AlbumCover> findAlbumCoverByAlbumImage(String albumImage);
}
