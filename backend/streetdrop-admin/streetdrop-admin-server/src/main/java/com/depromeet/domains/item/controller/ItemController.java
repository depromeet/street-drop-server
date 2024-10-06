package com.depromeet.domains.item.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.service.ItemService;
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
    public ResponseEntity<?> getAllItems(
            @PageableDefault(size = 20, page = 0, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "keyword", required = false) String keyword
    ) {
        var response = itemService.getAllItems(pageable, keyword);
        return ResponseDto.ok(response);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(
            @PathVariable(value = "itemId") Long itemId
    ) {
        itemService.deleteItem(itemId);
        return ResponseDto.noContent();
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> getItem(
            @PathVariable(value = "itemId") Long itemId
    ) {
        var response = itemService.getItem(itemId);
        return ResponseDto.ok(response);
    }
}
