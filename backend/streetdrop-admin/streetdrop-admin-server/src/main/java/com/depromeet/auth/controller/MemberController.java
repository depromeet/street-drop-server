package com.depromeet.auth.controller;

import com.depromeet.auth.entity.Member;
import com.depromeet.auth.service.AuthService;
import com.depromeet.auth.service.MemberService;
import com.depromeet.global.annotation.ReqMember;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(@ReqMember Member member) {
        return ResponseEntity.ok(memberService.getMyInfo(member));
    }

}
