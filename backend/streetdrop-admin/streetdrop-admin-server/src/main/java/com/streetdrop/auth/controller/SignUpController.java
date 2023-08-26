package com.streetdrop.auth.controller;

import com.streetdrop.auth.dto.request.SignupRequestDto;
import com.streetdrop.auth.dto.resonse.JwtTokenResponseDto;
import com.streetdrop.auth.service.MemberService;
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