package com.depromeet.domains.user.controller;

import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "내 정보 가져오기")
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUserInfo(
            @ReqUser User user
    ) {
        var response = userService.getUserInfo(user);
        return ResponseDto.ok(response);
    }

    @Operation(summary = "닉네임 변경하기")
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
    @PatchMapping("/music-app")
    public ResponseEntity<UserResponseDto> changeMusicApp(
            @ReqUser User user,
            @RequestParam("musicApp") MusicApp musicApp
    ) {
        var response = userService.changeMusicApp(user, musicApp);
        return ResponseDto.ok(response);
    }
}
