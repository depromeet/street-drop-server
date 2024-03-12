package com.depromeet.domains.notification.controller;

import com.depromeet.domains.notification.dto.request.TokenRequestDto;
import com.depromeet.domains.notification.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping
    public void saveToken(@RequestBody TokenRequestDto tokenRequestDto) {
        tokenService.saveToken(tokenRequestDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteToken(@PathVariable("userId") Long userId) {
        tokenService.deleteToken(userId);
    }

}
