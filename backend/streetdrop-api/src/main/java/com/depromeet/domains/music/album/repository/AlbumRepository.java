package com.depromeet.domains.music.album.repository;

import com.depromeet.music.album.Album;
import com.depromeet.music.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
	@Query("select a from Album a join fetch a.albumCover as cover where a.name = :albumName and cover.albumImage = :albumImage and a.artist = :artist")
	Optional<Album> findAlbumByNameAndImageAndArtist(@Param("albumName") String albumName, @Param("albumImage") String albumImage, @Param("artist") Artist artist);
}
