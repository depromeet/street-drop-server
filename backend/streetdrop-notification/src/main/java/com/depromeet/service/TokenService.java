package com.depromeet.service;

import com.depromeet.domain.UserDevice;
import com.depromeet.dto.request.TokenRequestDto;
import com.depromeet.repository.TokenRepository;
import com.depromeet.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserDeviceRepository userDeviceRepository;

    public void saveToken(TokenRequestDto tokenRequestDto) {
        UserDevice userDevice = UserDevice.builder()
                .userId(tokenRequestDto.getUserId())
                .deviceToken(tokenRequestDto.getToken())
                .build();
        userDeviceRepository.save(userDevice);
    }

    public void updateToken(TokenRequestDto tokenRequestDto) {
        var userDevice = userDeviceRepository.findByUserId(tokenRequestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("user not found"));
        userDevice.updateDeviceToken(tokenRequestDto.getToken());
        userDeviceRepository.save(userDevice);    }

    public void deleteToken(Long userId) {
        tokenRepository.delete(userId);
    }
}
