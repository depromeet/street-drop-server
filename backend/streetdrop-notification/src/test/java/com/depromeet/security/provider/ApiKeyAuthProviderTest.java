package com.depromeet.security.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.depromeet.error.code.GlobalErrorCode;
import com.depromeet.error.exceptions.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.util.ReflectionTestUtils;

class ApiKeyAuthProviderTest {

    private String secretKey = "test-secret-key";

    @InjectMocks
    private ApiKeyAuthProvider apiKeyAuthProvider;

    @BeforeEach
    public void setup() {
        apiKeyAuthProvider = new ApiKeyAuthProvider();
        ReflectionTestUtils.setField(apiKeyAuthProvider, "secretKey", secretKey);
    }

    @Test
    @DisplayName("인증 성공 - 올바른 API 키가 제공되었을 때")
    void authenticateSuccessWithValidKeyTest() {
        Authentication authentication = new PreAuthenticatedAuthenticationToken(secretKey, null);
        Authentication result = apiKeyAuthProvider.authenticate(authentication);

        assertThat(result.isAuthenticated()).isTrue();
        assertThat(result.getPrincipal()).isEqualTo(secretKey);
        assertThat(result.getCredentials()).isNull();
    }

    @Test
    @DisplayName("인증 실패 - 잘못된 API 키가 제공되었을 때")
    void authenticateFailWithValidKeyTest() {
        String invalidKey = "invalid-test-secret-key";
        Authentication authentication = new PreAuthenticatedAuthenticationToken(invalidKey, null);

        assertThatThrownBy(() -> apiKeyAuthProvider.authenticate(authentication))
                .isInstanceOf(UnauthorizedException.class)
                .hasMessageContaining(GlobalErrorCode.UNAUTHORIZED.getMessage());
    }

}