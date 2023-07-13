package com.depromeet.domains.item.service;

import com.depromeet.area.village.VillageArea;
import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.item.dto.request.*;
import com.depromeet.domains.item.dto.response.ItemResponseDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.repository.ItemLocationRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.music.service.MusicService;
import com.depromeet.domains.user.repository.BlockUserRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.domains.village.service.VillageAreaService;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLocation;
import com.depromeet.user.User;
import com.depromeet.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.depromeet.item.QItem.item;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final MusicService musicService;
	private final ItemRepository itemRepository;
	private final ItemLocationRepository itemLocationRepository;
	private final VillageAreaService villageAreaService;
	private final BlockUserRepository blockUserRepository;
	private final UserRepository userRepository;


	public PoiResponseDto findNearItemsPoints(User user, NearItemPointRequestDto nearItemPointRequestDto) {
		Point point = GeomUtil.createPoint(nearItemPointRequestDto.getLongitude(), nearItemPointRequestDto.getLatitude());

		List<Long> blockedUserIds = new ArrayList<>();
		if (user != null) {
			blockedUserIds = getBlockedUserIds(user);
		}
		var poiDtoList = itemLocationRepository.findNearItemsPointsByDistance(point, nearItemPointRequestDto.getDistance(), nearItemPointRequestDto.getInnerDistance(), blockedUserIds)
				.stream()
				.map(PoiResponseDto.PoiDto::fromItemPoint).toList();
		return new PoiResponseDto(poiDtoList);
	}

	@Transactional
	public ItemResponseDto create(User user, ItemCreateRequestDto itemCreateRequestDto) {
		var song = musicService.getOrCreateMusic(itemCreateRequestDto.getMusic());

		var item = Item.builder()
				.user(user)
				.albumCover(song.getAlbum().getAlbumCover())
				.song(song)
				.content(itemCreateRequestDto.getContent())
				.build();

		ItemLocationRequestDto locationRequestDto = itemCreateRequestDto.getLocation();
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
    public ItemsResponseDto findNearItems(User user, NearItemRequestDto nearItemRequestDto) {
        Point point = GeomUtil.createPoint(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude());
        var items = itemRepository.findNearItemsByDistance(point, nearItemRequestDto.getDistance());

		if (user != null) {
			final List<Long> blockedUserIds =  getBlockedUserIds(user);
			var itemDetailDtoList = items.stream()
					.filter((item) -> !blockedUserIds.contains(item.getUser().getId()))
					.map(ItemsResponseDto.ItemDetailDto::new)
					.toList();
			return new ItemsResponseDto(itemDetailDtoList);
		} else {
			final List<Long> blockedUserIds = new ArrayList<>();
			var itemDetailDtoList = items.stream()
					.map(ItemsResponseDto.ItemDetailDto::new)
					.toList();
			return new ItemsResponseDto(itemDetailDtoList);
		}
    }

	private List<Long> getBlockedUserIds(User user) {
		return blockUserRepository.findBlockedIdsByBlockerId(user.getId());
	}

	@Transactional(readOnly = true)
	public Item getItem(Long itemId) {
		return itemRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, String.valueOf(itemId)));
	}

	@Transactional
	public void delete(User user, Long itemId) {
		var item = itemRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, itemId));
		var userIdfv = item.getUser().getIdfv();

		if (!userIdfv.equals(user.getIdfv())) {
			throw new BusinessException(ErrorCode.INVALID_USER_EXCEPTION);
		}
		itemRepository.deleteById(itemId);
	}

	@Transactional
	public ItemResponseDto update(User user, Long itemId, ItemUpdateRequestDto itemRequestDto) {
		var item = itemRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, itemId));

		if (!item.getUser().getIdfv().equals(user.getIdfv())) {
			throw new BusinessException(ErrorCode.INVALID_USER_EXCEPTION);
		}
		item.updateContent(itemRequestDto.getContent());
		return new ItemResponseDto(item);
	}
}
