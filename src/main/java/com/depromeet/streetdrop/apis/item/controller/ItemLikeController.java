package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemLikeService;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.global.annotation.ReqUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items like", description = "Item Like API")
public class ItemLikeController {
	private final ItemLikeService itemLikeService;

	@Operation(summary = "아이템 좋아요")
	@PostMapping("/{itemId}/likes")
	public ResponseEntity<ItemLikeResponseDto> likeItem(
			@PathVariable Long itemId,
			@ReqUser User user
	) {
		var response = itemLikeService.likeItem(itemId, user);
		return ResponseDto.ok(response);
	}

	@Operation(summary = "아이템 좋아요 취소")
	@PostMapping("/{itemId}/unlikes")
	public ResponseEntity<Void> unlikeItem(
			@PathVariable Long itemId,
			@ReqUser User user
	) {
		itemLikeService.unlikeItem(itemId, user);
		return ResponseDto.noContent();
	}
}
