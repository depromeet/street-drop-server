package com.depromeet.domains.item.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.domains.item.service.ItemLikeService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
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
			@ReqUser User user,
			@PathVariable Long itemId
	) {
		var response = itemLikeService.likeItem(user, itemId);
		return ResponseDto.ok(response);
	}

	@Operation(summary = "아이템 좋아요 취소")
	@PostMapping("/{itemId}/unlikes")
	public ResponseEntity<Void> unlikeItem(
			@ReqUser User user,
			@PathVariable Long itemId

	) {
		itemLikeService.unlikeItem(user, itemId);
		return ResponseDto.noContent();
	}
}
