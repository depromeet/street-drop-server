package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.item.repository.ItemRepository;
import com.depromeet.streetdrop.domains.itemLocation.entity.ItemLocation;
import com.depromeet.streetdrop.domains.music.album.entity.AlbumCover;
import com.depromeet.streetdrop.domains.music.album.service.AlbumService;
import com.depromeet.streetdrop.domains.music.artist.entity.Artist;
import com.depromeet.streetdrop.domains.music.song.entity.Song;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.global.common.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
	public static final String TEST_USER = "User1";
	private final AlbumService albumService;
	private final ItemRepository itemRepository;
	private final ItemLocationRepository itemLocationRepository;
	private final static int WGS84_SRID = 4326;
	private final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), WGS84_SRID);

	public PoiResponseDto findNearItemsPoints(NearItemRequestDto nearItemRequestDto) {
		Point point = gf.createPoint(new Coordinate(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude()));
		var poiDtoList = itemLocationRepository.findNearItemsPointsByDistance(point, nearItemRequestDto.getDistance())
				.stream().map(PoiResponseDto.PoiDto::fromItemPoint).toList();
		return new PoiResponseDto(poiDtoList);
	}

	@Transactional
	public Item register(Long memberId, ItemRequestDto requestDto) {
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

		var album = albumService.getAlbum(requestDto.getAlbumName(), artist);

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

		return itemRepository.save(item);
	}
}
