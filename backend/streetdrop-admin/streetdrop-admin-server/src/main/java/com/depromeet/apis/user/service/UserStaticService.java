package com.depromeet.apis.user.service;

import com.depromeet.apis.user.dto.UserAllStaticCountDto;
import com.depromeet.apis.user.dto.UserSignUpCountRequestDto;
import com.depromeet.apis.user.dto.UserSignUpCountResponseDto;
import com.depromeet.apis.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStaticService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserSignUpCountResponseDto> getUserSignUpCount(UserSignUpCountRequestDto userSignUpCountRequestDto) {
        return userRepository.countUserByCreatedAt(userSignUpCountRequestDto.getStartDate(), userSignUpCountRequestDto.getEndDate())
                .stream()
                .map(row -> {
                    String joinDate = (String) row[0];
                    Long count = (Long) row[1];
                    return new UserSignUpCountResponseDto(joinDate, count);
                }).toList();
    }

    @Transactional(readOnly = true)
    public UserAllStaticCountDto getAllUserCount() {
        Long allUserCount = userRepository.count();
        int todayUserCount = userRepository.countUserByCreatedAt(LocalDateTime.now().minusDays(1), LocalDateTime.now()).size();
        int dropUserCount = userRepository.countUserByDropCountIsNotNull();
        return new UserAllStaticCountDto(allUserCount, todayUserCount, dropUserCount);
    }
}
