package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.PaginationResponseDto;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.dto.request.ItemOrderType;
import com.depromeet.domains.user.dto.response.UserItemCountGroupByLocationDto;
import com.depromeet.domains.user.dto.response.UserItemLocationCountDto;
import com.depromeet.domains.user.dto.response.UserPoiResponseDto;
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
@RequestMapping("/users/me/items")
@Tag(name = "🏃User Item", description = "User Item API")
public class UserItemController {

    private final UserItemService userItemService;

    @Operation(summary = "사용자가 드랍한 아이템 조회")
    @ApiResponse(responseCode = "200", description = "사용자가 드랍한 아이템 조회 성공")
    @GetMapping("/drop")
    public ResponseEntity<PaginationResponseDto<?, ?>> getUserDropItems(
            @ReqUser User user,
            @RequestParam(defaultValue = "RECENT", required = false) ItemOrderType orderType,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(defaultValue = "9223372036854775000") long lastCursor
    ) {
        var response = userItemService.getDropItems(user, lastCursor, orderType, state, city);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자가 드랍한 아이템 조회 - POI")
    @ApiResponse(responseCode = "200", description = "사용자가 드랍한 아이템 조회 성공")
    @GetMapping("/drop/points")
    public ResponseEntity<UserPoiResponseDto> getUserDropItemsPoints(
            @ReqUser User user
    ) {
        var response = userItemService.getDropItemsPoints(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자가 드랍한 아이템 지역 별 개수 조회")
    @ApiResponse(responseCode = "200", description = "사용자가 드랍한 아이템 지역별 개수 조회 성공")
    @GetMapping("/drop/count/all-locations")
    public ResponseEntity<UserItemCountGroupByLocationDto> countUserItemsGroupByStates(
            @ReqUser User user
    ) {
        var response = userItemService.countUserItemsGroupByLocation(user);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사용자가 드랍한 아이템 개수 조회")
    @ApiResponse(responseCode = "200", description = "사용자가 드랍한 아이템 개수 조회 성공")
    @GetMapping("/drop/count")
    public ResponseEntity<UserItemLocationCountDto> countUserItemsByLocation(
            @ReqUser User user,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city
    ) {
        var response = userItemService.countUserItemsByLocation(user, state, city);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사용자가 찜한 아이템 조회")
    @ApiResponse(responseCode = "200", description = "사용자가 찜한 아이템 조회 성공")
    @GetMapping("/like")
    public ResponseEntity<PaginationResponseDto<?, ?>> getUserLikedItems(
            @ReqUser User user,
            @RequestParam(defaultValue = "9223372036854775000") long lastCursor,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(defaultValue = "RECENT", required = false) ItemOrderType orderType
    ) {
        var response = userItemService.getLikedItems(user, lastCursor, orderType, state, city);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자가 찜한 아이템 조회 - POI")
    @ApiResponse(responseCode = "200", description = "사용자가 찜한 아이템 POI 조회 성공")
    @GetMapping("/like/points")
    public ResponseEntity<UserPoiResponseDto> getUserLikedItemsPoints(
            @ReqUser User user
    ) {
        var response = userItemService.getLikedItemsPoints(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자가 찜한 아이템 지역 별 개수 조회")
    @ApiResponse(responseCode = "200", description = "사용자가 찜한 아이템 지역별 개수 조회 성공")
    @GetMapping("/like/count/all-locations")
    public ResponseEntity<UserItemCountGroupByLocationDto> countLikedItemsGroupByStates(
            @ReqUser User user
    ) {
        var response = userItemService.countLikedItemsGroupByLocation(user);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "사용자가 찜한 아이템 개수 조회")
    @ApiResponse(responseCode = "200", description = "사용자가 찜한 아이템 개수 조회 성공")
    @GetMapping("/like/count")
    public ResponseEntity<UserItemLocationCountDto> countUserLikedItemsByLocation(
            @ReqUser User user,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "city", required = false) String city
    ) {
        var response = userItemService.countLikedItemsByLocation(user, state, city);
        return ResponseEntity.ok(response);
    }

}
