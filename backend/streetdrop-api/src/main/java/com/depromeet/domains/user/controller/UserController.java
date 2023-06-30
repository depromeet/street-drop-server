package com.depromeet.domains.user.controller;

import com.depromeet.domains.user.dto.response.UserResponseDto;
import com.depromeet.domains.user.service.UserService;
import com.depromeet.security.annotation.ReqUser;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "사용자 뮤직 앱 변경")
    @PatchMapping("/music-app")
    public ResponseEntity<UserResponseDto> changeMusicAPp(
            @ReqUser User user,
            @RequestParam("musicApp") String musicApp
    ) {
        var response = userService.changeMusicApp(user, musicApp);
        return ResponseDto.ok(response);
    }
}
