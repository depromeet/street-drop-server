package com.depromeet.domains.item.controller;

import com.depromeet.domains.item.dto.request.ItemCreateRequestDto;
import com.depromeet.domains.item.dto.request.ItemUpdateRequestDto;
import com.depromeet.domains.item.dto.response.ItemResponseDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.service.ItemService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.dto.request.NearItemPointRequestDto;
import com.depromeet.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.user.User;
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

    @Operation(summary = "주변 아이템 조회 - POI")
    @GetMapping("/points")
    public ResponseEntity<PoiResponseDto> findNearItemsPoints(@Valid NearItemPointRequestDto nearItemPointRequestDto) {
        var response = itemService.findNearItemsPoints(nearItemPointRequestDto);
        return ResponseDto.ok(response);
    }

	@Operation(summary = "아이템 드랍 - 등록")
	@PostMapping
	public ResponseEntity<ItemResponseDto> create(
            @ReqUser User user,
            @Valid @RequestBody ItemCreateRequestDto itemRequestDto) {
		var response = itemService.create(user, itemRequestDto);
		return ResponseDto.created(response);
	}

	@Operation(summary = "아이템 드랍 - 수정")
	@PatchMapping("/{itemId}")
	public ResponseEntity<ItemResponseDto> update(
			@ReqUser User user,
			@PathVariable(value = "itemId") Long itemId,
			@Valid @RequestBody ItemUpdateRequestDto itemUpdateRequestDto
	) {
		var response = itemService.update(user, itemId, itemUpdateRequestDto);
		return ResponseDto.ok(response);
	}

	@Operation(summary = "드랍 아이템 삭제")
	@DeleteMapping("/{itemId}")
	public ResponseEntity<Void> delete(
			@ReqUser User user,
			@PathVariable(value = "itemId") Long itemId
	) {
		itemService.delete(user, itemId);
		return ResponseDto.noContent();
	}

    @Operation(summary = "주변 아이템 상세 조회")
    @GetMapping
    public ResponseEntity<ItemsResponseDto> findNearItems(
			@ReqUser User user,
            @Valid NearItemRequestDto nearItemRequestDto
    ) {
        var response = itemService.findNearItems(user, nearItemRequestDto);
        return ResponseEntity.ok(response);
    }
}
