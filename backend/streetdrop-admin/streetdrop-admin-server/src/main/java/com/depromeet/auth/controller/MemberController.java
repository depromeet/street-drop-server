package com.depromeet.auth.controller;

import com.depromeet.auth.dto.request.ChangePasswordRequestDto;
import com.depromeet.auth.entity.Member;
import com.depromeet.auth.service.MemberService;
import com.depromeet.common.dto.ResponseDto;
import com.depromeet.global.annotation.ReqMember;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(@ReqMember Member member) {
        return ResponseEntity.ok(memberService.getMyInfo(member));
    }

    @GetMapping
    public ResponseEntity<?> getAllMembers(){
        var response = memberService.getAllMembers();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/me/change-password")
    public ResponseEntity<?> changePassword(
            @ReqMember Member member,
            @RequestBody ChangePasswordRequestDto changePasswordRequestDto
    ){
        memberService.changePassword(member, changePasswordRequestDto.getPrevPassword(), changePasswordRequestDto.getNewPassword());
        return ResponseDto.noContent();
    }

}
