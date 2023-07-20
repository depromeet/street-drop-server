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

    private final LocalDateTime serviceStartDate = LocalDateTime.of(2023, 7, 13, 0, 0, 0);

    @Transactional(readOnly = true)
    public List<UserSignUpCountResponseDto> getUserSignUpCount(UserSignUpCountRequestDto userSignUpCountRequestDto) {
        LocalDateTime startDate = userSignUpCountRequestDto.getStartDate();
        LocalDateTime endDate = userSignUpCountRequestDto.getEndDate();
        if (startDate.isBefore(serviceStartDate)) {
            startDate = serviceStartDate;
        }
        return userRepository.countUserByCreatedAt(startDate, endDate)
                .stream()
                .map(row -> {
                    String joinDate = (String) row[0];
                    Long count = (Long) row[1];
                    return new UserSignUpCountResponseDto(joinDate, count);
                }).toList();
    }

    @Transactional(readOnly = true)
    public UserAllStaticCountDto getAllUserCount() {
        Long allUserCount = userRepository.countAllByCreatedAtAfter();
        int todayUserCount = userRepository.countUserByCreatedAt(LocalDateTime.now().minusDays(1), LocalDateTime.now()).size();
        int dropUserCount = userRepository.countUserByDropCountIsNotNull();
        return new UserAllStaticCountDto(allUserCount, todayUserCount, dropUserCount);
    }
}
