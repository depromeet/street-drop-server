package com.depromeet.domains.user.controller;

import com.depromeet.common.dto.ResponseDto;
import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.domains.user.dto.response.UserLevelResponseDto;
import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.service.UserLevelService;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.external.swagger.annotation.ApiErrorResponse;
import com.depromeet.external.aws.s3.AwsS3Service;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "👨Users", description = "User API")
public class UserController {
    private final UserService userService;
    private final UserLevelService userLevelService;
    private final AwsS3Service awsS3Service;

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
            @RequestParam("nickname") @NotNull(message = "Nickname is required")
            @Size(min = 1, max = 10, message = "닉네임은 한글자 이상 10글자 이하입니다.") String nickname
    ) {
        var response = userService.changeNickname(user, nickname);
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
    @ApiErrorResponse(errorCode = ErrorCode.NOT_FOUND, description = "사용자 유저 레벨이 존재하지 않음")
    @GetMapping("/me/level")
    public ResponseEntity<UserLevelResponseDto> getUserLevel(@ReqUser User user) {
        var response = userLevelService.getUserLevel(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "사용자 프로필 이미지 수정")
    @PatchMapping(value = "/me/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> changeProfileImage(
            @ReqUser User user,
            @RequestPart(value = "file") MultipartFile multipartFile
    ) {
        String imageUrl = awsS3Service.updateFileToS3(multipartFile, "PROFILE");
        var response = userService.changeImageUrlById(user, imageUrl);
        return ResponseDto.ok(response);
    }
}
