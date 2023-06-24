package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.village.service.VillageAreaService;
import com.depromeet.area.village.VillageArea;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLocation;
import com.depromeet.domains.item.dto.request.ItemLocationRequestDto;
import com.depromeet.domains.item.dto.request.ItemRequestDto;
import com.depromeet.domains.item.dto.request.NearItemPointRequestDto;
import com.depromeet.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.domains.item.dto.response.ItemResponseDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.repository.ItemLocationRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.music.service.MusicService;
import com.depromeet.user.User;
import com.depromeet.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final MusicService musicService;
	private final ItemRepository itemRepository;
	private final ItemLocationRepository itemLocationRepository;
	private final VillageAreaService villageAreaService;

	public PoiResponseDto findNearItemsPoints(NearItemPointRequestDto nearItemPointRequestDto) {
		Point point = GeomUtil.createPoint(nearItemPointRequestDto.getLongitude(), nearItemPointRequestDto.getLatitude());
		var poiDtoList = itemLocationRepository.findNearItemsPointsByDistance(point, nearItemPointRequestDto.getDistance(), nearItemPointRequestDto.getInnerDistance())
				.stream().map(PoiResponseDto.PoiDto::fromItemPoint).toList();
		return new PoiResponseDto(poiDtoList);
	}

	@Transactional
	public ItemResponseDto create(User user, ItemRequestDto itemRequestDto) {
		var song = musicService.getOrCreateMusic(itemRequestDto.getMusic());

		var item = Item.builder()
				.user(user)
				.albumCover(song.getAlbum().getAlbumCover())
				.song(song)
				.content(itemRequestDto.getContent())
				.build();

		ItemLocationRequestDto locationRequestDto = itemRequestDto.getLocation();
		Point point = GeomUtil.createPoint(locationRequestDto.getLongitude(), locationRequestDto.getLatitude());
		VillageArea villageArea = villageAreaService.getVillageByLocationPoint(point);

		ItemLocation itemLocation = ItemLocation.builder()
				.name(locationRequestDto.getAddress())
				.item(item)
				.villageArea(villageArea)
				.point(point)
				.build();

		item.setItemLocation(itemLocation);
		var savedItem = itemRepository.save(item);

		return new ItemResponseDto(savedItem);
	}

	@Transactional(readOnly = true)
    public ItemsResponseDto findNearItems(NearItemRequestDto nearItemRequestDto) {
        Point point = GeomUtil.createPoint(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude());
        var items = itemRepository.findNearItemsByDistance(point, nearItemRequestDto.getDistance());
        var itemDetailDtoList = items.stream()
                .map(ItemsResponseDto.ItemDetailDto::new)
                .toList();
        return new ItemsResponseDto(itemDetailDtoList);
    }

	@Transactional(readOnly = true)
	public Item getItem(Long itemId) {
		return itemRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, String.valueOf(itemId)));
	}
}
