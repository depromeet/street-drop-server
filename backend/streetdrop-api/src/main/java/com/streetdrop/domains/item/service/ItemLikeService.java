package com.streetdrop.domains.item.service;

import com.streetdrop.common.error.dto.ErrorCode;
import com.streetdrop.common.error.exception.common.BusinessException;
import com.streetdrop.item.Item;
import com.streetdrop.item.ItemLike;
import com.streetdrop.domains.item.dto.response.ItemLikeResponseDto;
import com.streetdrop.user.User;
import com.streetdrop.domains.item.repository.ItemLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
			throw new BusinessException(ErrorCode.ALREADY_ITEM_LIKED_ERROR);
		}
	}

	@Transactional
	public void unlikeItem(User user, Long itemId) {
		itemLikeRepository.findByItemIdAndUser(itemId, user)
				.ifPresent(itemLikeRepository::delete);
	}
}
