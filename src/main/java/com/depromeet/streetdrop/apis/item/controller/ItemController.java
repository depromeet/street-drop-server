package com.depromeet.streetdrop.apis.item.controller;

import com.depromeet.streetdrop.domains.common.dto.PageResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Items API")
public class ItemController {
    private final ItemService itemService;

    @Operation(summary = "다중 아이템 상세 조회")
    @GetMapping
    public ResponseEntity<PageResponseDto<ItemDetailResponseDto>> findNearItems(
            @RequestParam Double longitude, @RequestParam Double latitude,
            @PageableDefault(size = 20) Pageable pageable
    ) {
        var response = itemService.findNearItems(longitude, latitude, pageable);
        return PageResponseDto.ok(response);
    }

}
