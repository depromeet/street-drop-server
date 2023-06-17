package com.depromeet.music.song.repository;

import com.depromeet.music.album.Album;
import com.depromeet.music.song.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
	Optional<Song> findSongByNameAndAlbum(String name, Album album);
}
