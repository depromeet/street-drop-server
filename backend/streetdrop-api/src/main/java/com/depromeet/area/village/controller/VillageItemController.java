package com.depromeet.area.village.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.area.village.dto.request.VillageItemsRequestDto;
import com.depromeet.area.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.area.village.service.VillageItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/villages/items")
@RequiredArgsConstructor
@Tag(name = "Village Items", description = "Village Items API")
public class VillageItemController {

    private final VillageItemService villageItemService;

    @Operation(summary = "동별 드랍 아이템 개수 조회")
    @GetMapping("/count")
    public ResponseEntity<VillageItemsCountResponseDto> countItemsInVillageByLocation(
            @Valid VillageItemsRequestDto villageItemsRequestDto
    ) {
        var response = villageItemService.countItemsInVillageByLocation(villageItemsRequestDto);
        return ResponseDto.ok(response);
    }

}
