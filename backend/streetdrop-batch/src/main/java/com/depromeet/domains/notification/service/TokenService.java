package com.depromeet.domains.notification.service;

import com.depromeet.common.error.code.GlobalErrorCode;
import com.depromeet.domains.notification.dto.request.TokenRequestDto;
import com.depromeet.common.error.code.TokenErrorCode;
import com.depromeet.common.error.exceptions.NotFoundException;
import com.depromeet.domains.user.repository.UserDeviceRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.User;
import com.depromeet.user.UserDevice;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;
    private final UserDeviceRepository userDeviceRepository;

    @Transactional
    public void saveToken(TokenRequestDto tokenRequestDto) {
        User user = userRepository.findById(tokenRequestDto.getUserId())
                .orElseThrow(() -> new NotFoundException(GlobalErrorCode.USER_NOT_FOUND));
        Optional<UserDevice> userDevice = userDeviceRepository.findByUserId(tokenRequestDto.getUserId());
        if (userDevice.isPresent()) {
            UserDevice updatedUserDevice = userDevice.get().updateDeviceToken(tokenRequestDto.getToken());
            userDeviceRepository.save(updatedUserDevice);
        }
        else {
            UserDevice createdUserDevice = UserDevice.builder()
                    .user(user)
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
