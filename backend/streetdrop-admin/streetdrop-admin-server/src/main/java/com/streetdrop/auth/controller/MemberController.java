package com.streetdrop.auth.controller;

import com.streetdrop.auth.entity.Member;
import com.streetdrop.auth.service.MemberService;
import com.streetdrop.global.annotation.ReqMember;
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
