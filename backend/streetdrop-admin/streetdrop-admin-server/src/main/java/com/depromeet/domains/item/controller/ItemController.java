package com.depromeet.domains.item.controller;

import com.depromeet.domains.item.dto.ItemAllResponseDto;
import com.depromeet.domains.user.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    @GetMapping
    public ResponseEntity<ItemAllResponseDto> getAllItems(
            @PageableDefault(size = 20, page = 0, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var response = itemService.getAllItems(pageable);
        return ResponseDto.ok(response);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteItem(
            @PathVariable(value = "itemId") Long itemId
    ) {
        itemService.deleteItem(itemId);
        return ResponseDto.noContent();
    }
}
