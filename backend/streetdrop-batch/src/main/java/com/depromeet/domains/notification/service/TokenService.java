package com.depromeet.domains.notification.service;

import com.depromeet.domains.notification.dto.request.TokenRequestDto;
import com.depromeet.common.error.code.TokenErrorCode;
import com.depromeet.common.error.exceptions.NotFoundException;
import com.depromeet.domains.notification.repository.UserDeviceRepository;
import com.depromeet.domains.notification.domain.UserDevice;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        var userDevice = userDeviceRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(TokenErrorCode.TOKEN_NOT_FOUND));
        userDeviceRepository.delete(userDevice);
    }
}
