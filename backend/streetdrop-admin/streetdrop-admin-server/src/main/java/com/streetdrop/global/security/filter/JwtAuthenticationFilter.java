package com.streetdrop.global.security.filter;


import com.streetdrop.auth.entity.Member;
import com.streetdrop.auth.repository.MemoryMemberRepository;
import com.streetdrop.exception.BusinessException;
import com.streetdrop.exception.ErrorResponse;
import com.streetdrop.global.security.provider.SecurityUserDetails;
import com.streetdrop.global.security.token.JwtTokenResolver;
import com.streetdrop.global.security.token.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;
    private final MemoryMemberRepository memberRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            final Optional<String> resolveToken = JwtTokenResolver.resolveToken(request);
            if (resolveToken.isEmpty()) {
                chain.doFilter(request, response);
                return;
            }
            String token = resolveToken.get();

            var id = jwtTokenUtil.getId(token);
            System.out.println(id);
            Member member = memberRepository.findById(Long.valueOf(id))
                    .orElseThrow(() ->
                            new RuntimeException("Invalid member")
                    );
            var userDetails = new SecurityUserDetails(member);
            var auth = new UsernamePasswordAuthenticationToken(userDetails, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BusinessException e) {
            response.setContentType("application/json");
            response.setStatus(e.getErrorCode().getStatus().value());
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            response.getWriter().write(mapper.writeValueAsString(
                    ErrorResponse.builder()
                            .status(e.getErrorCode().getStatus().value())
                            .code(e.getErrorCode().getCode())
                            .message(e.getErrorCode().getMessage())
                            .build())
            );
            return;
        } catch (Exception e) {
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/login");
    }

}
