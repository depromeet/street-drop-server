package com.streetdrop.domains.item.service;

import com.streetdrop.area.village.VillageArea;
import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.BusinessException;
import com.streetdrop.common.error.exception.common.NotFoundException;
import com.streetdrop.domains.item.dto.request.*;
import com.streetdrop.domains.item.dto.response.*;
import com.streetdrop.domains.item.repository.ItemLikeRepository;
import com.streetdrop.domains.item.repository.ItemLocationRepository;
import com.streetdrop.domains.item.repository.ItemRepository;
import com.streetdrop.domains.music.service.MusicService;
import com.streetdrop.domains.user.dto.response.UserProfileResponseDto;
import com.streetdrop.domains.user.repository.BlockUserRepository;
import com.streetdrop.domains.village.service.VillageAreaService;
import com.streetdrop.item.Item;
import com.streetdrop.item.ItemLocation;
import com.streetdrop.user.User;
import com.streetdrop.util.GeomUtil;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
	private final MusicService musicService;
	private final ItemRepository itemRepository;
	private final ItemLocationRepository itemLocationRepository;
	private final VillageAreaService villageAreaService;
	private final BlockUserRepository blockUserRepository;
	private final ItemLikeRepository itemLikeRepository;


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
	public ItemDetailResponseDto findOneItem(User user, Long itemId) {
		var item = itemRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, itemId));
		var itemLikeCount = itemLikeRepository.countByItemId(itemId);
		var isLiked = itemLikeRepository.existsByUserIdAndItemId(user.getId(), itemId);

		var musicResponseDto = musicService.getMusic(item.getSong().getId());
		var userProfileResponseDto = new UserProfileResponseDto(item.getUser().getId(), item.getUser().getNickname());
		var itemLocationResponseDto = new ItemLocationResponseDto(item.getItemLocation().getName());

		return ItemDetailResponseDto.builder()
				.itemId(item.getId())
				.music(musicResponseDto)
				.user(userProfileResponseDto)
				.location(itemLocationResponseDto)
				.content(item.getContent())
				.createdAt(item.getCreatedAt())
				.isLiked(isLiked)
				.itemLikeCount(itemLikeCount)
				.build();
	}

	@Transactional(readOnly = true)
    public ItemsResponseDto findNearItems(User user, NearItemRequestDto nearItemRequestDto) {
		Point point = GeomUtil.createPoint(nearItemRequestDto.getLongitude(), nearItemRequestDto.getLatitude());
		var items = itemRepository.findNearItemsByDistance(point, nearItemRequestDto.getDistance());

		final List<Long> blockedUserIds = getBlockedUserIds(user);
		var itemDetailDtoList = items.stream()
				.filter((item) -> !blockedUserIds.contains(item.getUser().getId()))
				.map(item ->
				{
					Boolean isLiked = itemLikeRepository.existsByUserIdAndItemId(user.getId(), item.getId());
					return new ItemsResponseDto.ItemDetailDto(item, isLiked);
				})
				.toList();
		return new ItemsResponseDto(itemDetailDtoList);
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
