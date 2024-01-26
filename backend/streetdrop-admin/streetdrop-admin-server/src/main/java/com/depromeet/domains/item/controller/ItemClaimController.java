package com.depromeet.domains.item.controller;


import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.dto.ItemClaimRequestDto;
import com.depromeet.domains.item.service.ItemClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items/claim")
public class ItemClaimController {

    private final ItemClaimService itemClaimService;

    @GetMapping
    public ResponseEntity<?> getClaimItem(
            @PageableDefault(
                    size = 20,
                    page = 0,
                    sort = "createdAt",
                    direction = DESC
            ) Pageable pageable
    ) {
        var result = itemClaimService.getClaimItem(pageable);
        return ResponseDto.ok(result);
    }

    @PatchMapping
    public ResponseEntity<?> updateClaimItem(
            @RequestBody ItemClaimRequestDto itemClaimRequestDto
    ) {
        var result = itemClaimService.updateClaimItem(itemClaimRequestDto.getItemClaimId(), itemClaimRequestDto.getItemClaimStatus());
        return ResponseDto.ok(result);
    }

}
