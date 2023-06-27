package com.depromeet.domains.item.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.service.ItemClaimService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Tag(name = "Items Claim", description = "Item Claim API")
public class ItemClaimController {
    private final ItemClaimService itemClaimService;

    @Operation(summary = "아이템 신고하기")
    @PostMapping("/claim")
    public ResponseEntity<Void> claimItem(
            @ReqUser User user,
            @Valid @RequestBody ItemClaimRequestDto itemClaimRequestDto
    ) {
        itemClaimService.claimItem(user, itemClaimRequestDto);
        return ResponseDto.noContent();
    }
}
