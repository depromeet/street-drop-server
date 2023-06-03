package com.depromeet.streetdrop.domains.item.service;

import com.depromeet.streetdrop.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.streetdrop.domains.item.entity.Item;
import com.depromeet.streetdrop.domains.item.entity.ItemLike;
import com.depromeet.streetdrop.domains.item.repository.ItemLikeRepository;
import com.depromeet.streetdrop.domains.item.repository.ItemRepository;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.global.error.dto.ErrorCode;
import com.depromeet.streetdrop.global.error.exception.common.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemLikeService {
	private final ItemRepository itemRepository;
	private final ItemLikeRepository itemLikeRepository;

	@Transactional
	public ItemLikeResponseDto likeItem(Long itemId, User user) {
		Optional<Item> itemOptional = itemRepository.findById(itemId);
		if (itemOptional.isPresent()) {
			var item = itemOptional.get();
			checkUserAlreadyLike(user, item);
			var itemLike = ItemLike.builder()
					.item(item)
					.user(user)
					.build();

			itemLikeRepository.save(itemLike);
			return new ItemLikeResponseDto(itemLike);
		}
		throw new NotFoundException(ErrorCode.NOT_FOUND);
	}

	private void checkUserAlreadyLike(User user, Item item) {
		if (item.getLikes().stream().anyMatch(like -> like.getUser().getId().equals(user.getId()))) {
			throw new IllegalArgumentException("User has already liked the item");
		}
	}

	@Transactional
	public void unlikeItem(Long itemId, User user) {
		Optional<Item> itemOptional = itemRepository.findById(itemId);
		if (itemOptional.isPresent()) {
			var item = itemOptional.get();
			ItemLike itemLike = itemLikeRepository.findByItemAndUser(item, user);
			if (itemLike != null) {
				itemLikeRepository.delete(itemLike);
			}
		}
	}
}
