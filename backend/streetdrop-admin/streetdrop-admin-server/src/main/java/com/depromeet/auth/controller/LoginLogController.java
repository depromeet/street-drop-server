package com.depromeet.auth.controller;

import com.depromeet.auth.service.LoginLogService;
import com.depromeet.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/login-log")
@RequiredArgsConstructor
public class LoginLogController {
    private final LoginLogService loginLogService;
    @GetMapping
    public ResponseEntity<?> getAllLoginLog() {
        var response = loginLogService.getAllLoginLog();
        return ResponseDto.ok(response);
    }
}
