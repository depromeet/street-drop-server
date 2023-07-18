package com.depromeet.item;

import com.depromeet.common.entity.BaseTimeEntity;
import com.depromeet.music.album.AlbumCover;
import com.depromeet.music.song.Song;
import com.depromeet.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Item extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "item_id")
	private Long id;

	@Column(length = 500, nullable = false)
	private String content;

	@OneToOne(fetch = LAZY, mappedBy = "item", cascade = ALL)
	private ItemLocation itemLocation;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "song_id", nullable = false)
	private Song song;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "album_cover_id", nullable = false)
	private AlbumCover albumCover;

	@OneToMany(mappedBy = "item", cascade = ALL)
	private List<ItemLike> likes;

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

	public int getItemLikeCount() {
		return likes != null ? likes.size() : 0;
	}

	public boolean isLiked(User user) {
		return likes != null && likes.stream().anyMatch(like -> like.isLiked(user));
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
