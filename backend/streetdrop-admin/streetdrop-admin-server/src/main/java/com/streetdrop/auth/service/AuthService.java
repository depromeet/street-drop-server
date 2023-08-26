package com.streetdrop.auth.service;

import com.streetdrop.auth.dto.resonse.JwtTokenResponseDto;
import com.streetdrop.auth.repository.MemberRepository;
import com.streetdrop.exception.BusinessException;
import com.streetdrop.exception.ErrorCode;
import com.streetdrop.global.security.token.JwtTokenProvider;
import com.streetdrop.global.security.token.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenUtil jwtTokenUtil;
    private final MemberRepository memberRepository;

    public JwtTokenResponseDto reissueToken(String refreshToken) {
        try {
            var id = jwtTokenUtil.getId(refreshToken);
            memberRepository.findById(Long.valueOf(id))
                    .orElseThrow(() ->
                            new BusinessException(ErrorCode.NOT_VALID_MEMBER)
                    );
            String accessToken = jwtTokenProvider.createAccessToken(id);
            return new JwtTokenResponseDto(accessToken, refreshToken);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
    }
}
