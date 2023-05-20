package com.depromeet.streetdrop.domains.item.facade;

import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import com.depromeet.streetdrop.domains.itemLocation.service.ItemLocationService;
import com.depromeet.streetdrop.domains.music.album.service.AlbumCoverService;
import com.depromeet.streetdrop.domains.music.album.service.AlbumService;
import com.depromeet.streetdrop.domains.music.artist.service.ArtistService;
import com.depromeet.streetdrop.domains.music.song.service.SongService;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.domains.user.service.UserService;
import com.depromeet.streetdrop.global.common.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ItemFacade {
	private final ItemService itemService;
	private final ItemLocationService itemLocationService;
	private final UserService userService;
	private final AlbumService albumService;
	private final ArtistService artistService;
	private final SongService songService;
	private final AlbumCoverService albumCoverService;
	public static final String TEST_USER = "User1";

	@Transactional
	public Item register(ItemRequestDto requestDto) {
		var location = itemLocationService.create(requestDto);
		var user = userService.getOrCreateUser(TEST_USER);
		var artist = artistService.getOrCreateArtist(requestDto.getArtiest());
		var album = albumService.getOrCreateAlbum(requestDto.getAlbumName(), artist);
		var albumCover = albumCoverService.getOrCreateAlbumCover(album, requestDto.getAlbumImage(), requestDto.getAlbumImage());
		var song = songService.getOrCreateSong(requestDto.getTitle(), album);

		var item = Item.builder()
				.user(user)
				.itemLocation(location)
				.albumCover(albumCover)
				.song(song)
				.content(requestDto.getContent())
				.build();

		return itemService.create(item);
	}
}
