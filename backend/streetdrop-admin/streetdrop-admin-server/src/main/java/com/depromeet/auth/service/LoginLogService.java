package com.depromeet.auth.service;

import com.depromeet.auth.dto.resonse.MemberLoginLogResponseDto;
import com.depromeet.auth.repository.MemberLoginLogRepository;
import com.depromeet.common.dto.PageMetaData;
import com.depromeet.common.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginLogService {

    private final MemberLoginLogRepository memberLoginLogRepository;

    public PageResponseDto<MemberLoginLogResponseDto> getAllLoginLog() {
        var memberLoginLogList = memberLoginLogRepository.findAll();
        List<MemberLoginLogResponseDto> loginLogResponseDtoList = memberLoginLogList.stream().map(
                memberLoginLog -> MemberLoginLogResponseDto.builder()
                        .id(memberLoginLog.getId())
                        .userAgent(memberLoginLog.getUserAgent())
                        .loginIp(memberLoginLog.getLoginIp())
                        .loginAt(memberLoginLog.getCreatedAt())
                        .loginResult(memberLoginLog.getLoginResult())
                        .build()
        ).toList();

        var pageMetaData = new PageMetaData(
                1,
                30,
                loginLogResponseDtoList.size(),
                1
        );


        return new PageResponseDto<>(loginLogResponseDtoList, pageMetaData);
    }
}
