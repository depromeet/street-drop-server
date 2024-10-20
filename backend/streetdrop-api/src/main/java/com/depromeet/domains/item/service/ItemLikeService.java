package com.depromeet.domains.item.service;

import com.depromeet.common.dto.InfiniteScrollMetaResponseDto;
import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.domains.item.dao.UserItemLikeDao;
import com.depromeet.domains.item.dto.response.ItemGroupByDateResponseDto;
import com.depromeet.domains.item.dto.response.ItemGroupResponseDto;
import com.depromeet.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.domains.item.dto.response.ItemLocationResponseDto;
import com.depromeet.domains.item.error.ItemErrorCode;
import com.depromeet.domains.item.repository.ItemLikeRepository;
import com.depromeet.domains.music.dto.response.MusicResponseDto;
import com.depromeet.domains.user.dto.request.ItemOrderType;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLike;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.depromeet.util.WeekUtil.getWeeksAgo;

@Service
@RequiredArgsConstructor
public class ItemLikeService {
	private final ItemService itemService;
	private final ItemLikeRepository itemLikeRepository;

	@Transactional
	public ItemLikeResponseDto likeItem(User user, Long itemId) {
		var item = itemService.getItem(itemId);
		checkUserAlreadyLike(user, item);

		var itemLike = ItemLike.builder()
				.item(item)
				.user(user)
				.build();
		itemLikeRepository.save(itemLike);
		return new ItemLikeResponseDto(user, itemLike);
	}

	private void checkUserAlreadyLike(User user, Item item) {
		boolean alreadyLiked = itemLikeRepository.existsByUserIdAndItemId(user.getId(), item.getId());
		if (alreadyLiked) {
			throw new BusinessException(ItemErrorCode.ITEM_ALREADY_LIKED);
		}
	}

	@Transactional
	public void unlikeItem(User user, Long itemId) {
		itemLikeRepository.findByItemIdAndUser(itemId, user)
				.ifPresent(itemLikeRepository::delete);
	}

	@Transactional(readOnly = true)
	public PaginationResponseDto<?,?> getLikedItemsByUser(User user, long lastCursor, ItemOrderType itemOrderType, String state, String city) {

		List<UserItemLikeDao> itemLikeDaoList = getLikedItemListByUser(user, lastCursor, itemOrderType, state, city);

		List<ItemGroupByDateResponseDto> itemGroupByDateResponseDto = itemLikeDaoList
				.stream()
				.map(UserItemLikeDao::getWeekAgo)
				.distinct()
				.map(value -> {
					List<ItemGroupResponseDto> itemGroupResponseDtoList = itemLikeDaoList.stream()
							.filter(userItemLikeDao -> userItemLikeDao.getWeekAgo() == value)
							.map(this::userItemLikeDaotoItemGroupResponseDto)
							.toList();
					return new ItemGroupByDateResponseDto(getWeeksAgo(value), itemGroupResponseDtoList);
				})
				.toList();

		var meta = new InfiniteScrollMetaResponseDto(itemLikeDaoList.size(), -1);

		return new PaginationResponseDto<>(itemGroupByDateResponseDto, meta);
	}


	private List<UserItemLikeDao> getLikedItemListByUser(User user, long lastCursor, ItemOrderType itemOrderType, String state, String city){
		if (state == null) {
			return itemLikeRepository.findByUserId(user.getId(), lastCursor, itemOrderType);
		}
		else if (city == null) {
			return itemLikeRepository.findByUserIdAndState(user.getId(), lastCursor, itemOrderType, state);
		}
		else {
			return itemLikeRepository.findByUserIdAndCity(user.getId(), lastCursor, itemOrderType, city);
		}
	}

	private ItemGroupResponseDto userItemLikeDaotoItemGroupResponseDto(UserItemLikeDao userItemLikeDao) {
		return ItemGroupResponseDto
				.builder()
				.itemId(userItemLikeDao.getItemId())
				.user(new UserResponseDto(userItemLikeDao.getUserId(), userItemLikeDao.getNickname()))
				.location(new ItemLocationResponseDto(userItemLikeDao.getLocationName()))
				.music(
						MusicResponseDto.builder()
								.title(userItemLikeDao.getSongName())
								.artist(userItemLikeDao.getArtistName())
								.albumImage(userItemLikeDao.getAlbumThumbnail())
								.genre(List.of())
								.build()
				)
				.content(userItemLikeDao.getContent())
				.createdAt(userItemLikeDao.getCreatedAt())
				.itemLikeCount(userItemLikeDao.getItemCount())
				.build();
	}
}
