package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.item.repository.ItemLocationRepository;
import com.depromeet.streetdrop.domains.item.repository.ItemRepository;
import com.depromeet.streetdrop.domains.itemLocation.service.ItemLocationService;
import com.depromeet.streetdrop.domains.music.service.MusicService;
import com.depromeet.streetdrop.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final ItemLocationService itemLocationService;
	private final UserService userService;
	private final MusicService musicService;
	private final ItemRepository itemRepository;
	private final ItemLocationRepository itemLocationRepository;

	public static final String TEST_USER = "User1";
	private final static int WGS84_SRID = 4326;
	private final GeometryFactory gf = new GeometryFactory(new PrecisionModel(PrecisionModel.FLOATING), WGS84_SRID);

	public PoiResponseDto findNearItemsPoints(NearItemRequestDto nearItemRequestDto) {
		Point point = gf.createPoint(new Coordinate(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude()));
		var poiDtoList = itemLocationRepository.findNearItemsPointsByDistance(point, nearItemRequestDto.getDistance())
				.stream().map(PoiResponseDto.PoiDto::fromItemPoint).toList();
		return new PoiResponseDto(poiDtoList);
	}

	@Transactional
	public ItemResponseDto create(ItemRequestDto requestDto) {
		var user = userService.getOrCreateUser(TEST_USER);
		var location = itemLocationService.create(requestDto);
		var artist = musicService.getOrCreateArtist(requestDto.getMusic());
		var album = musicService.getOrCreateAlbum(requestDto.getMusic(), artist);
		var albumCover = musicService.getOrCreateAlbumCover(requestDto.getMusic(), album);
		var song = musicService.getOrCreateSong(requestDto.getMusic(), album);

		var item = Item.builder()
				.user(user)
				.itemLocation(location)
				.albumCover(albumCover)
				.song(song)
				.content(requestDto.getContent())
				.build();
		var savedItem = itemRepository.save(item);
		musicService.updateAlbumByAlbumCover(album, albumCover);
		itemLocationService.updateLocationByItemId(location, savedItem);
		return new ItemResponseDto(savedItem);
	}

	@Transactional(readOnly = true)
    public List<ItemDetailResponseDto> findNearItems(NearItemRequestDto nearItemRequestDto) {
        Point point = gf.createPoint(new Coordinate(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude()));
        var items = itemRepository.findNearItemsByDistance(point, nearItemRequestDto.getDistance());
        var response = items.stream()
                .map(ItemDetailResponseDto::new)
                .toList();
        return response;
    }

}
