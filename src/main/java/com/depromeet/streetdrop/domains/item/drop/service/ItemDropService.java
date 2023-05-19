package com.depromeet.streetdrop.domains.item.drop.service;

import com.depromeet.streetdrop.domains.item.drop.dto.ItemDropRequestDto;
import com.depromeet.streetdrop.domains.item.drop.entity.Item;
import com.depromeet.streetdrop.domains.item.drop.repository.ItemDropRepository;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import com.depromeet.streetdrop.domains.music.album.entity.Album;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.global.common.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ItemDropService {
	public static final String TEST_USER = "User1";
	private final ItemDropRepository itemDropRepository;

	@Transactional
	public Item register(Long memberId, ItemDropRequestDto requestDto) {
		Double lat = Double.parseDouble(requestDto.getLatitude());
		Double lon = Double.parseDouble(requestDto.getLongitude());
		Point point = GeomUtil.createPoint(lat, lon);

		var itemLocation = ItemLocation.builder()
				.name(requestDto.getAddress())
				.point(point)
				.build();

		// TODO: 로그인 연동 후 유저 정보를 받아오도록 수정 필요
		var user = User.builder()
				.nickname(TEST_USER)
				.build();

		var artist = Artist.builder()
				.name(requestDto.getArtiest())
				.build();

		var album = Album.builder()
				.name(requestDto.getAlbumName())
				.songs(new ArrayList<Song>())
				.artist(artist)
				.build();

		var albumCover = AlbumCover.builder()
				.albumImage(requestDto.getAlbumImage())
				.albumThumbnail(requestDto.getAlbumImage())
				.build();

		var song = Song.builder()
				.name(requestDto.getTitle())
				.album(album)
				.build();

		var item = Item.builder()
				.user(user)
				.itemLocation(itemLocation)
				.albumCover(albumCover)
				.song(song)
				.content(requestDto.getContent())
				.build();

		return itemDropRepository.save(item);
	}
}
