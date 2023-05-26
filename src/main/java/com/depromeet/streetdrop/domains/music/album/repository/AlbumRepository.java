package com.depromeet.streetdrop.domains.music.album.repository;

import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
	@Query("select a from Album a join fetch a.albumCover as cover where a.name = :albumName and cover.albumImage = :albumImage and a.artist = :artist")
	Optional<Album> findAlbumByNameAndImageAndArtist(String albumName, String albumImage, Artist artist);
}
