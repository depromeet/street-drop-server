package com.depromeet.apis.user.controller;

import com.depromeet.apis.user.dto.UserAllStaticCountDto;
import com.depromeet.apis.user.dto.UserSignUpCountRequestDto;
import com.depromeet.apis.user.dto.UserSignUpCountResponseDto;
import com.depromeet.apis.user.service.UserStaticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users/statical")
@RequiredArgsConstructor
public class UserStaticalController {

    private final UserStaticService userService;

    @GetMapping("/signup/count")
    public ResponseEntity<List<UserSignUpCountResponseDto>> getUserSignUpCount(
            UserSignUpCountRequestDto userSignUpCountRequestDto
    ) {
        var response = userService.getUserSignUpCount(userSignUpCountRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/count")
    public ResponseEntity<UserAllStaticCountDto> getAllUserCount() {
        var response = userService.getAllUserCount();
        return ResponseEntity.ok(response);
    }

}
