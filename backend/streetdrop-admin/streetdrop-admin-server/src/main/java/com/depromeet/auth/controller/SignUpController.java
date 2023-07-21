package com.depromeet.auth.controller;

import com.depromeet.auth.dto.request.SignupRequestDto;
import com.depromeet.auth.dto.resonse.JwtTokenResponseDto;
import com.depromeet.auth.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class SignUpController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<JwtTokenResponseDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        var response = memberService.signUp(signupRequestDto);
        return ResponseEntity.ok(response);
    }

}