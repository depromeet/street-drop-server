package com.depromeet.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLike;
import com.depromeet.item.dto.response.ItemLikeResponseDto;
import com.depromeet.user.User;
import com.depromeet.item.repository.ItemLikeRepository;
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
