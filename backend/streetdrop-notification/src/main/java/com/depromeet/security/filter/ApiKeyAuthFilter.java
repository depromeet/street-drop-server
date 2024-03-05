package com.depromeet.security.filter;

import com.depromeet.security.provider.ApiKeyAuthProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class ApiKeyAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Value("${auth.header}")
    private String header;

    public ApiKeyAuthFilter(ApiKeyAuthProvider apiKeyAuthProvider) {
        super.setCheckForPrincipalChanges(true);
        super.setAuthenticationManager(new ProviderManager(apiKeyAuthProvider));
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader(header);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return null;
    }

}
