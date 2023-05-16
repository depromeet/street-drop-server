package com.depromeet.streetdrop.apis.area.village.controller;

import com.depromeet.streetdrop.apis.area.village.dto.response.VillageItemsCountResponseDto;
import com.depromeet.streetdrop.domains.area.village.service.VillageItemService;
import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/villages/items")
@RequiredArgsConstructor
@Tag(name = "Village Items", description = "Village Items API")
public class VillageItemController {

    private final VillageItemService villageItemService;

    @Operation(summary = "동별 드랍 아이템 개수 조회")
    @GetMapping("/count")
    public ResponseEntity<VillageItemsCountResponseDto> countItemsByVillage(
            @Schema(description = "동 이름", example = "종로구 사직동")
            @RequestParam(value = "name") String villageName
    ) {
        var response = villageItemService.countItemsByVillage(villageName);
        return ResponseDto.ok(response);
    }

}
