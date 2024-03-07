package com.depromeet.security.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApiKeyAuthFilterTest {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private ApiKeyAuthFilter apiKeyAuthFilter;

    @Test
    @DisplayName("헤더가 있는 경우, 인증 키를 읽는다")
    void getHeaderWithExistingHeaderTest() {
        String apiKey = "test-api-key";
        when(request.getHeader(any())).thenReturn(apiKey);

        Object result = apiKeyAuthFilter.getPreAuthenticatedPrincipal(request);

        assertThat(result).isEqualTo(apiKey);
    }

    @Test
    @DisplayName("헤더가 없는 경우, null을 반환한다")
    void getNullIWithNotExistingHeaderTest() {
        when(request.getHeader(any())).thenReturn(null);

        Object result = apiKeyAuthFilter.getPreAuthenticatedPrincipal(request);

        assertThat(result).isNull();
    }

}
