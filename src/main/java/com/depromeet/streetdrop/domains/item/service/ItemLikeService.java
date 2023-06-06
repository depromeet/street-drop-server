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

import java.util.Optional;

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
		return new ItemLikeResponseDto(user, itemLike);
	}

	private void checkUserAlreadyLike(User user, Item item) {
		if (existItemLike(user, item)) {
			throw new AlreadyItemLikedException();
		}
	}

	private boolean existItemLike(User user, Item item) {
		return itemLikeRepository.existsByUserIdAndItemId(user.getId(), item.getId());
	}

	@Transactional
	public void unlikeItem(User user, Long itemId) {
		Optional<ItemLike> itemLikeOptional = itemLikeRepository.findByItemIdAndUser(itemId, user);
		if (itemLikeOptional.isPresent()) {
			var itemLike = itemLikeOptional.get();
			itemLikeRepository.delete(itemLike);
		}
	}
}
