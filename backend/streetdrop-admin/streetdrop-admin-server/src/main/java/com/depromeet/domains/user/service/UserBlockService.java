package com.depromeet.domains.user.service;

import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import com.depromeet.domains.user.dto.UserBlockResponseDto;
import com.depromeet.domains.user.repository.UserBlockRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.exception.BusinessException;
import com.depromeet.exception.ErrorCode;
import com.depromeet.user.BlockUser;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserBlockService {

    private final UserBlockRepository userBlockRepository;
    private final UserRepository userRepository;
    @Transactional(readOnly = true)
    public PageResponseDto<UserBlockResponseDto> getUserBlockList(Pageable pageable) {
        Page<BlockUser> userBlockResult = userBlockRepository.findAll(pageable);
        PageMetaData pageMetaData = new PageMetaData(
                userBlockResult.getNumber(),
                userBlockResult.getSize(),
                (int) userBlockResult.getTotalElements(),
                userBlockResult.getTotalPages()
        );

        List<UserBlockResponseDto> data = userBlockResult.getContent()
                .stream()
                .map((userBlock) ->
                {
                    User blockerUser = userRepository.findById(userBlock.getBlockerId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
                    User blockedUser = userRepository.findById(userBlock.getBlockedId())
                            .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND));
                    return UserBlockResponseDto.builder()
                            .id(userBlock.getId())
                            .userId(blockerUser.getId())
                            .userNickname(blockerUser.getNickname())
                            .blockUserId(blockedUser.getId())
                            .blockUserNickname(blockedUser.getNickname())
                            .createdAt(userBlock.getCreatedAt())
                            .build();
                }).toList();

        return new PageResponseDto<>(data, pageMetaData);
    }
}
