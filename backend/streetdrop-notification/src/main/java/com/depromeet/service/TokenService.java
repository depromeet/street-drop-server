package com.depromeet.service;

import com.depromeet.domain.UserDevice;
import com.depromeet.dto.request.TokenRequestDto;
import com.depromeet.repository.UserDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserDeviceRepository userDeviceRepository;

    @Transactional
    public void saveToken(TokenRequestDto tokenRequestDto) {
        Optional<UserDevice> userDevice = userDeviceRepository.findByUserId(tokenRequestDto.getUserId());
        if (userDevice.isPresent()) {
            UserDevice updatedUserDevice = userDevice.get().updateDeviceToken(tokenRequestDto.getToken());
            userDeviceRepository.save(updatedUserDevice);
        } else {
            UserDevice createdUserDevice = UserDevice.builder()
                    .userId(tokenRequestDto.getUserId())
                    .deviceToken(tokenRequestDto.getToken())
                    .build();
            userDeviceRepository.save(createdUserDevice);
        }
    }

    @Transactional
    public void deleteToken(Long userId) {
        userDeviceRepository.deleteByUserId(userId);
    }
}
