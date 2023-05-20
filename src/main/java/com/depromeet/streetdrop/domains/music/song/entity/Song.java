package com.depromeet.streetdrop.domains.music.song.entity;

import com.depromeet.streetdrop.domains.common.BaseTimeEntity;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.genre.entity.SongGenre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Song extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "song_id")
	private Long id;

	private String name;

	@ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "album_id")
	private Album album;

	@OneToMany(mappedBy = "song")
	private List<SongGenre> genres = new ArrayList<>();

	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();

	@Builder
	public Song(String name, Album album, List<SongGenre> genres, List<Item> items) {
		this.name = name;
		this.album = album;
		this.genres = genres;
		this.items = items;
	}
	public List<Genre> getGenres() {
		return genres.stream()
				.map(SongGenre::getGenre)
				.toList();
	}

}
