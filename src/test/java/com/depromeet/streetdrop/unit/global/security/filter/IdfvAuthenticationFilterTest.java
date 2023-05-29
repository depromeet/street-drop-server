package com.depromeet.streetdrop.unit.global.security.filter;

import com.depromeet.streetdrop.global.security.filter.IdfvAuthenticationFilter;
import com.depromeet.streetdrop.global.security.provider.IdfvUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IdfvAuthenticationFilterTest {

    private IdfvAuthenticationFilter idfvAuthenticationFilter;

    @Mock
    private IdfvUserDetailsService idfvUserDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    public void setup() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);
        idfvUserDetailsService = Mockito.mock(IdfvUserDetailsService.class);
    }

    @Test
    @DisplayName("x-sdp-idfv 헤더가 있는 경우, 유저를 인증한다.")
    public void testDoFilterInternal_WithValidIdfv_ShouldAuthenticateUser() throws Exception {
        String idfv = "valid-idfv";
        User userDetails = new User("username", "password", Collections.emptyList());
        when(request.getHeader("x-sdp-idfv")).thenReturn(idfv);
        when(idfvUserDetailsService.loadUserByUsername(idfv)).thenReturn(userDetails);

        idfvAuthenticationFilter = new IdfvAuthenticationFilter(idfvUserDetailsService) {
            public IdfvAuthenticationFilter callDoFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws Exception {
                doFilterInternal(request, response, filterChain);
                return this;
            }
        }.callDoFilterInternal(request, response, filterChain);

        verify(idfvUserDetailsService).loadUserByUsername(idfv);
        verify(filterChain).doFilter(request, response);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication).isInstanceOf(UsernamePasswordAuthenticationToken.class);
        assertThat(authentication.getPrincipal()).isEqualTo(userDetails);
        assertThat(authentication.getCredentials()).isNull();
    }

    @Test
    @DisplayName("x-sdp-idfv 헤더가 없는 경우, 필터체인을 실행한다.")
    public void testDoFilterInternal_WithOutValidIdfv() throws Exception {
        when(request.getHeader("x-sdp-idfv")).thenReturn(null);

        idfvAuthenticationFilter = new IdfvAuthenticationFilter(idfvUserDetailsService) {
            public IdfvAuthenticationFilter callDoFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws Exception {
                doFilterInternal(request, response, filterChain);
                return this;
            }
        }.callDoFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        verifyNoInteractions(idfvUserDetailsService);
    }


}
