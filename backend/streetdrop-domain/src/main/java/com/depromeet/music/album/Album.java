package com.depromeet.music.album;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.music.artist.Artist;
import com.depromeet.music.song.Song;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Album extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "album_id")
	private Long id;

	private String name;

	@OneToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "album_cover_id")
	private AlbumCover albumCover;

	@OneToMany(mappedBy = "album", fetch = LAZY, cascade = ALL, orphanRemoval = true)
	private List<Song> songs = new ArrayList<>();

	@ManyToOne(fetch = LAZY, cascade = ALL)
	@JoinColumn(name = "artist_id")
	private Artist artist;

	@Builder
	public Album(String name, AlbumCover albumCover, List<Song> songs, Artist artist) {
		this.name = name;
		this.albumCover = albumCover;
		this.songs = songs;
		this.artist = artist;
	}

	public void updateAlbumCover(AlbumCover albumCover) {
		this.albumCover = albumCover;
	}
}
