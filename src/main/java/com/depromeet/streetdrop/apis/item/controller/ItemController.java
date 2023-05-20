package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.ItemRequestDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/items", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Item Drop API")
public class ItemController {
	private final ItemService itemService;

	@Operation(summary = "드랍 아이템 등록")
	@PostMapping("/{memberId}")
	public ResponseEntity<Void> create(
			@PathVariable Long memberId,
			@RequestBody ItemRequestDto requestDto
	) {
		itemService.register(memberId, requestDto);
		return ResponseDto.noContent();
	}
}
