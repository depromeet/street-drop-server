package com.depromeet.domains.music.song.repository;

import com.depromeet.domains.music.song.entity.Song;
import com.depromeet.domains.music.album.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
	Optional<Song> findSongByNameAndAlbum(String name, Album album);
}