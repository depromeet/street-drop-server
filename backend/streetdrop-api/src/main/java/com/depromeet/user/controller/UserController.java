package com.depromeet.user.controller;

import com.depromeet.security.annotation.ReqUser;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.user.dto.response.UserResponseDto;
import com.depromeet.user.User;
import com.depromeet.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
