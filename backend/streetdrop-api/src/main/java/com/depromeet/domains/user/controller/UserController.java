package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.domains.user.dto.request.NicknameChangeDto;
import com.depromeet.domains.user.dto.response.UserDistanceResponseDto;
import com.depromeet.domains.user.dto.response.UserLevelResponseDto;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.service.UserLevelService;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "👨Users", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserLevelService userLevelService;

    @Operation(summary = "내 정보 가져오기")
    @ApiResponse(responseCode = "200", description = "내 정보 가져오기 성공")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserInfo(
            @ReqUser User user
    ) {
        var response = userService.getUserInfo(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "닉네임 변경하기")
    @ApiResponse(responseCode = "200", description = "닉네임 변경 성공")
    @PatchMapping("/me/nickname")
    public ResponseEntity<UserResponseDto> changeNickname(
            @ReqUser User user,
            @Valid NicknameChangeDto nicknameChangeDto
    ) {
        var response = userService.changeNickname(user, nicknameChangeDto.getNickname());
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자 뮤직 앱 변경")
    @ApiResponse(responseCode = "200", description = "사용자 레벨 조회 성공")
    @PatchMapping("/music-app")
    public ResponseEntity<UserResponseDto> changeMusicApp(
            @ReqUser User user,
            @RequestParam("musicApp") MusicApp musicApp
    ) {
        var response = userService.changeMusicApp(user, musicApp);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자 레벨 조회")
    @ApiResponse(responseCode = "200", description = "사용자 레벨 조회 성공")
    @ApiErrorResponse(errorCode = "COMMON_NOT_FOUND", description = "사용자 유저 레벨이 존재하지 않음")
    @GetMapping("/me/level")
    public ResponseEntity<UserLevelResponseDto> getUserLevel(@ReqUser User user) {
        var response = userLevelService.getUserLevel(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자 반경 조회")
    @ApiResponse(responseCode = "200", description = "사용자 반경 조회 성공")
    @GetMapping("/me/distance")
    public ResponseEntity<UserDistanceResponseDto> getUserDistance(@ReqUser User user) {
        var response = userService.getUserDistance(user);
        return ResponseDto.ok(response);
    }

}
