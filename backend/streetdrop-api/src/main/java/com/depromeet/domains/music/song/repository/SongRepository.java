package com.depromeet.domains.music.song.repository;

import com.depromeet.music.album.Album;
import com.depromeet.music.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long>, QueryDslSongRepository {
	Optional<Song> findSongByNameAndAlbum(String name, Album album);

	@Query("SELECT s FROM Song s JOIN FETCH s.album " +
			"JOIN FETCH s.genres AS g " +
			"JOIN FETCH g.genre " +
			"JOIN FETCH s.album.artist " +
			"JOIN FETCH s.album.albumCover WHERE s.id = :id")
	Optional<Song> findSongById(Long id);
}
