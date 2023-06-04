package com.depromeet.domains.music.album.repository;

import com.depromeet.domains.music.album.entity.AlbumCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumCoverRepository extends JpaRepository<AlbumCover, Long> {
	Optional<AlbumCover> findAlbumCoverByAlbumImage(String albumImage);
}
