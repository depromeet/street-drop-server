package com.depromeet.auth.controller;

import com.depromeet.auth.dto.request.ReissueTokenRequestDto;
import com.depromeet.auth.dto.resonse.JwtTokenResponseDto;
import com.depromeet.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissueToken(
            @RequestBody ReissueTokenRequestDto reissueTokenDto
    ) {
        var response = authService.reissueToken(reissueTokenDto.getRefreshToken());
        return ResponseEntity.ok(response);
    }

}
