package com.depromeet.domains.item.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.dto.request.ItemCreateRequestDto;
import com.depromeet.domains.item.dto.request.ItemUpdateRequestDto;
import com.depromeet.domains.item.dto.request.NearItemPointRequestDto;
import com.depromeet.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.domains.item.dto.response.ItemResponseDto;
import com.depromeet.domains.item.dto.response.ItemsResponseDto;
import com.depromeet.domains.item.dto.response.PoiResponseDto;
import com.depromeet.domains.item.service.ItemService;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import com.depromeet.external.swagger.annotation.ApiErrorResponses;
import com.depromeet.security.annotation.ReqUser;
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
@Tag(name = "📍Items", description = "Item API")
public class ItemController {
    private final ItemService itemService;

    @Operation(summary = "주변 아이템 조회 - POI")
    @GetMapping("/points")
	@ApiErrorResponse(errorCode = "GEO_NOT_SUPPORT_LOCATION", description = "현재 서비스가 제공되지 않는 지역입니다.")
    public ResponseEntity<PoiResponseDto> findNearItemsPoints(
			@ReqUser User user,
			@Valid NearItemPointRequestDto nearItemPointRequestDto) {
        var response = itemService.findNearItemsPoints(user, nearItemPointRequestDto);
        return ResponseDto.ok(response);
    }

	@Operation(summary = "아이템 드랍 - 등록")
	@PostMapping
	@ApiErrorResponses(value = {
			@ApiErrorResponse(errorCode = "GEO_NOT_SUPPORT_LOCATION", description = "현재 서비스가 제공되지 않는 지역입니다."),
			@ApiErrorResponse(errorCode = "COMMON_CAN_NOT_USE_BANNED_WORD", description = "사용할 수 없는 금칙어가 포함되어 있습니다.")
	})
	public ResponseEntity<ItemResponseDto> create(
            @ReqUser User user,
            @Valid @RequestBody ItemCreateRequestDto itemRequestDto) {
		var response = itemService.create(user, itemRequestDto);
		return ResponseDto.created(response);
	}

    @Operation(summary = "아이템 드랍 - 단건조회")
    @GetMapping("/{itemId}")
	@ApiErrorResponse(errorCode = "ITEM_NOT_FOUND", description = "아이템을 찾을 수 없습니다.")
    public ResponseEntity<ItemDetailResponseDto> findOneItem(
            @ReqUser User user,
            @PathVariable(value = "itemId") Long itemId
    ) {
        var response = itemService.findOneItem(user, itemId);
        return ResponseDto.ok(response);
    }

	@Operation(summary = "아이템 드랍 - 수정")
	@PatchMapping("/{itemId}")
	@ApiErrorResponse(errorCode = "ITEM_NOT_FOUND", description = "아이템을 찾을 수 없습니다.")
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
	@ApiErrorResponse(errorCode = "ITEM_NOT_FOUND", description = "아이템을 찾을 수 없습니다.")
	public ResponseEntity<Void> delete(
			@ReqUser User user,
			@PathVariable(value = "itemId") Long itemId
	) {
		itemService.delete(user, itemId);
		return ResponseDto.noContent();
	}

    @Operation(summary = "주변 아이템 상세 조회")
    @GetMapping
	@ApiErrorResponse(errorCode = "GEO_NOT_SUPPORT_LOCATION", description = "현재 서비스가 제공되지 않는 지역입니다.")
    public ResponseEntity<ItemsResponseDto> findNearItems(
			@ReqUser User user,
            @Valid NearItemRequestDto nearItemRequestDto
    ) {
        var response = itemService.findNearItems(user, nearItemRequestDto);
        return ResponseEntity.ok(response);
    }
}
