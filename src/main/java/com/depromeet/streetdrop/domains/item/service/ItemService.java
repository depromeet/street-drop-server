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
import com.depromeet.streetdrop.domains.user.entity.User;
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
	private final MusicService musicService;
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
	public ItemResponseDto create(User user, ItemRequestDto itemRequestDto) {
		var itemLocation = itemLocationService.create(itemRequestDto);
		var musicDto = musicService.getOrCreateMusic(itemRequestDto.getMusic());

		var item = Item.builder()
				.user(user)
				.itemLocation(itemLocation)
				.albumCover(musicDto.albumCover())
				.song(musicDto.song())
				.content(itemRequestDto.getContent())
				.build();

		var savedItem = itemRepository.save(item);
		musicService.updateAlbum(musicDto.album(), musicDto.albumCover());
		itemLocationService.updateItemLocation(itemLocation, savedItem);

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
