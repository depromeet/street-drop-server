package com.depromeet.auth.service;

import com.depromeet.auth.dto.resonse.MemberLoginLogAllResponseDto;
import com.depromeet.auth.dto.resonse.MemberLoginLogResponseDto;
import com.depromeet.auth.repository.MemoryMemberLoginLogRepository;
import com.depromeet.common.dto.PageMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginLogService {

    private final MemoryMemberLoginLogRepository memberLoginLogRepository;

    public MemberLoginLogAllResponseDto getAllLoginLog() {
        var memberLoginLogList = memberLoginLogRepository.findAll();
        List<MemberLoginLogResponseDto> loginLogResponseDtoList = memberLoginLogList.stream().map(
                memberLoginLog -> MemberLoginLogResponseDto.builder()
                        .id(memberLoginLog.getId())
                        .userId(memberLoginLog.getMember().getUsername())
                        .userAgent(memberLoginLog.getUserAgent())
                        .name(memberLoginLog.getMember().getName())
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


        return new MemberLoginLogAllResponseDto(loginLogResponseDtoList, pageMetaData);
    }
}
