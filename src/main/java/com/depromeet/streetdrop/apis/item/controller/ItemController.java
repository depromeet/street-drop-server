package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.request.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Item API")
public class ItemController {
    private final ItemService itemService;

    @Operation(summary = "주변 아이템 조회 - POI")
    @GetMapping("/points")
    public ResponseEntity<PoiResponseDto> findNearItemsPoints(@Valid NearItemRequestDto nearItemRequestDto) {
        var response = itemService.findNearItemsPoints(nearItemRequestDto);
        return ResponseDto.ok(response);
    }

	@Operation(summary = "아이템 드랍 - 등록")
	@PostMapping
	public ResponseEntity<ItemResponseDto> create(@Valid @RequestBody ItemRequestDto itemRequestDto) {
		var response = itemService.create(itemRequestDto);
		return ResponseDto.created(response);
	}

    @Operation(summary = "주변 아이템 상세 조회")
    @GetMapping
    public ResponseEntity<List<ItemDetailResponseDto>> findNearItems(
            @Valid NearItemRequestDto nearItemRequestDto
    ) {
        var response = itemService.findNearItems(nearItemRequestDto);
        return ResponseEntity.ok(response);
    }
}