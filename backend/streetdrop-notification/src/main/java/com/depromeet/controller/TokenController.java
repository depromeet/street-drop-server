package com.depromeet.controller;

import com.depromeet.dto.request.TokenRequestDto;
import com.depromeet.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
