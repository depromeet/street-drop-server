package com.depromeet.security.filter;

import com.depromeet.security.provider.IdfvUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class IdfvAuthenticationFilter extends OncePerRequestFilter {

    private final IdfvUserDetailsService idfvUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String idfv = request.getHeader("x-sdp-idfv");

        if (idfv == null) {
            chain.doFilter(request, response);
            return;
        }

        var userDetails = idfvUserDetailsService.loadUserByUsername(idfv);
        var auth = new UsernamePasswordAuthenticationToken(userDetails, null, null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
}
