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
    public void createToken(@RequestBody TokenRequestDto tokenRequestDto) {
        tokenService.createToken(tokenRequestDto);
    }

    @PutMapping
    public void updateToken(@RequestBody TokenRequestDto tokenRequestDto) {
        tokenService.updateToken(tokenRequestDto);
    }

    @DeleteMapping
    public void deleteToken(@RequestBody Long userId) {
        tokenService.deleteToken(userId);
    }

}
