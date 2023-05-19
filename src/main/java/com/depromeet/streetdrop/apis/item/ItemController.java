package com.depromeet.streetdrop.apis.item;

import com.depromeet.streetdrop.domains.common.dto.ResponseDto;
import com.depromeet.streetdrop.domains.item.dto.response.ItemDetailResponseDto;
import com.depromeet.streetdrop.domains.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items", description = "Items API")
public class ItemController {
    private final ItemService itemService;

    @Operation(summary = "다중 아이템 상세 조회")
    @GetMapping
    public ResponseEntity<List<ItemDetailResponseDto>> getItems(
            @RequestParam Double longitude, @RequestParam Double latitude
    ) {
        var response = itemService.findAll(longitude, latitude);
        return ResponseDto.ok(response);  // TODO: paging 처리
    }

}
