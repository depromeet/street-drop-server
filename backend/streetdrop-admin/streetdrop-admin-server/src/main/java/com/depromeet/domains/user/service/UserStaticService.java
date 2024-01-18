package com.depromeet.domains.user.service;

import com.depromeet.domains.user.dto.UserAllStaticCountDto;
import com.depromeet.domains.user.dto.UserSignUpCountRequestDto;
import com.depromeet.domains.user.dto.UserSignUpCountResponseDto;
import com.depromeet.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.dd");

        List<Object[]> countResults = userRepository.countUserByCreatedAt(startDate, endDate);


        Map<String, Long> countMap = countResults.stream()
                .collect(Collectors.toMap(row -> (String) row[0], row -> (Long) row[1]));


        List<LocalDate> dateRange = Stream.iterate(startDate.toLocalDate(), date ->
                !date.isAfter(endDate.toLocalDate()), date -> date.plusDays(1)).toList();

        return dateRange.stream()
                .map(date -> new UserSignUpCountResponseDto(date.format(formatter), countMap.getOrDefault(date.toString(), 0L)))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserAllStaticCountDto getAllUserCount() {
        Long allUserCount = userRepository.countAllByCreatedAtAfter();
        int todayUserCount = userRepository.countUserByCreatedAt(LocalDateTime.now().minusDays(1), LocalDateTime.now()).size();
        int dropUserCount = userRepository.countUserByDropCountIsNotNull();
        return new UserAllStaticCountDto(allUserCount, todayUserCount, dropUserCount);
    }
}
