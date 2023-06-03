package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemLikeService;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import com.depromeet.streetdrop.domains.user.entity.User;
import com.depromeet.streetdrop.global.annotation.ReqUser;
import com.depromeet.streetdrop.global.error.exception.common.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Item API")
public class ItemController {
    private final ItemService itemService;
	private final ItemLikeService itemLikeService;

    @Operation(summary = "주변 아이템 조회 - POI")
    @GetMapping("/points")
    public ResponseEntity<PoiResponseDto> findNearItemsPoints(@Valid NearItemRequestDto nearItemRequestDto) {
        var response = itemService.findNearItemsPoints(nearItemRequestDto);
        return ResponseDto.ok(response);
    }

	@Operation(summary = "아이템 드랍 - 등록")
	@PostMapping
	public ResponseEntity<ItemResponseDto> create(
            @ReqUser User user,
            @Valid @RequestBody ItemRequestDto itemRequestDto) {
		var response = itemService.create(user, itemRequestDto);
		return ResponseDto.created(response);
	}

    @Operation(summary = "주변 아이템 상세 조회")
    @GetMapping
    public ResponseEntity<ItemsResponseDto> findNearItems(
            @Valid NearItemRequestDto nearItemRequestDto
    ) {
        var response = itemService.findNearItems(nearItemRequestDto);
        return ResponseEntity.ok(response);
    }

	@Operation(summary = "아이템 좋아요")
	@PostMapping("/{itemId}/likes")
	public ResponseEntity<ItemLikeResponseDto> likeItem(
			@PathVariable Long itemId,
			@ReqUser User user
	) {
		try {
			var response = itemLikeService.likeItem(itemId, user);
			return ResponseDto.ok(response);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "아이템 좋아요 취소")
	@DeleteMapping("/{itemId}/unlikes")
	public ResponseEntity<Void> unlikeItem(
			@PathVariable Long itemId,
			@ReqUser User user
	) {
		try {
			itemLikeService.unlikeItem(itemId, user);
			return ResponseDto.noContent();
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
