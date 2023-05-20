package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
    public ResponseEntity<PoiResponseDto> findNearItemsPoints(@Valid NearItemRequestDto nearItemRequestDto) {
        var response = itemService.findNearItemsPoints(nearItemRequestDto);
        return ResponseDto.ok(response);
    }

	@Operation(summary = "드랍 아이템 등록")
	@PostMapping("/")
	public ResponseEntity<Void> create(@RequestBody ItemRequestDto requestDto) {
		// TODO: 로그인 적용 후 MEMBER ID 변경 필요
		Long memberId = Long.valueOf(RandomStringUtils.random(15, false, true));
		itemService.register(memberId, requestDto);
		return ResponseDto.noContent();
	}

}
