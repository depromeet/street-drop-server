package com.depromeet.domains.item.entity;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.domains.music.album.entity.AlbumCover;
import com.depromeet.domains.music.song.entity.Song;
import com.depromeet.domains.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Item extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_id")
	private Long id;

	@Column(length = 500)
	private String content;

	@OneToOne(fetch = LAZY, mappedBy = "item", cascade = CascadeType.ALL)
	private ItemLocation itemLocation;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "song_id")
	private Song song;


	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "album_cover_id")
	private AlbumCover albumCover;

	@Builder
	public Item(String content, ItemLocation itemLocation, User user, Song song, AlbumCover albumCover) {
		this.content = content;
		this.itemLocation = itemLocation;
		this.user = user;
		this.song= song;
		this.albumCover = albumCover;
	}

	public void setItemLocation(ItemLocation itemLocation) {
		this.itemLocation = itemLocation;
	}
}
