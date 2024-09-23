package com.depromeet.domains.music.song.repository;

import com.depromeet.music.album.Album;
import com.depromeet.music.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

	@Query(nativeQuery = true,
			value = "SELECT DISTINCT s.* FROM (" +
					"   SELECT s.* FROM song s " +
					"   JOIN item i ON s.song_id = i.song_id " +
					"   ORDER BY i.created_at DESC " +
					"   LIMIT :count" +
					") s")
	List<Song> findRecentSongs(@Param("count") int count);

}
