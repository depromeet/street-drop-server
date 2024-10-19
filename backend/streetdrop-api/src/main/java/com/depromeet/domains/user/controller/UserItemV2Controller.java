package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.dto.request.ItemOrderType;
import com.depromeet.domains.user.service.UserItemService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/users/me/items")
@Tag(name = "🏃User Item", description = "User Item API")
public class UserItemV2Controller {

    private final UserItemService userItemService;

    @Operation(summary = "사용자가 드랍한 아이템 조회 V2")
    @ApiResponse(responseCode = "200", description = "사용자가 드랍한 아이템 조회 성공")
    @GetMapping("/drop")
    public ResponseEntity<PaginationResponseDto<?, ?>> getUserDropItemsV2(
            @ReqUser User user,
            @RequestParam(defaultValue = "RECENT", required = false) ItemOrderType orderType,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(defaultValue = "9223372036854775000") long lastCursor
    ) {
        var response = userItemService.getDropItemsV2(user, lastCursor, orderType, state, city);
        return ResponseDto.ok(response);
    }

}
