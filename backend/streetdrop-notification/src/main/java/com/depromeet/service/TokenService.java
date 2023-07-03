package com.depromeet.service;

import com.depromeet.dto.request.TokenRequestDto;
import com.depromeet.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void createToken(TokenRequestDto tokenRequestDto) {
        tokenRepository.save(tokenRequestDto.getUserId(), tokenRequestDto.getToken());
    }

    public void updateToken(TokenRequestDto tokenRequestDto) {
        tokenRepository.update(tokenRequestDto.getUserId(), tokenRequestDto.getToken());
    }
}
