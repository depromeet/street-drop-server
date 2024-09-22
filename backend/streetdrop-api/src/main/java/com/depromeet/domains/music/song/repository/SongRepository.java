package com.depromeet.domains.music.song.repository;

import com.depromeet.music.album.Album;
import com.depromeet.music.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
	Optional<Song> findSongByNameAndAlbum(String name, Album album);

	@Query("SELECT s FROM Song s JOIN FETCH s.album " +
			"JOIN FETCH s.genres AS g " +
			"JOIN FETCH g.genre " +
			"JOIN FETCH s.album.artist " +
			"JOIN FETCH s.album.albumCover WHERE s.id = :id")
	Optional<Song> findSongById(Long id);

	@Query("SELECT i.song FROM Item i " +
			"ORDER BY i.createdAt DESC")
	List<Song> findRecentSongs(int count);  // JPQL은 LIMIT 절을 지원하지 않음

}
