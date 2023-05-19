package com.depromeet.streetdrop.apis.item;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.request.NearItemRequestDto;
import com.depromeet.streetdrop.domains.item.dto.response.PoiResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Item API")
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "주변 아이템 조회 - POI")
    @GetMapping
    public ResponseEntity<PoiResponseDto> getNearItemPoints(@Valid NearItemRequestDto nearItemRequestDto) {
        var response = itemService.getNearItemPoints(nearItemRequestDto);
        return ResponseDto.ok(response);
    }

}
