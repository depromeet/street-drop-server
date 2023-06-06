package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.item.entity.ItemLike;
import com.depromeet.streetdrop.domains.item.repository.ItemLikeRepository;
import com.depromeet.streetdrop.domains.item.repository.ItemRepository;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.global.error.exception.item.AlreadyItemLikedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemLikeService {
	private final ItemService itemService;
	private final ItemRepository itemRepository;
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
		return new ItemLikeResponseDto(itemLike);
	}

	private void checkUserAlreadyLike(User user, Item item) {
		if (item.getLikes().stream().anyMatch(like -> like.getUser().getId().equals(user.getId()))) {
			throw new AlreadyItemLikedException();
		}
	}

	@Transactional
	public void unlikeItem(User user, Long itemId) {
		ItemLike itemLike = itemLikeRepository.findByItemIdAndUser(itemId, user);
		if (itemLike != null) {
			itemLikeRepository.delete(itemLike);
		}
	}
}
