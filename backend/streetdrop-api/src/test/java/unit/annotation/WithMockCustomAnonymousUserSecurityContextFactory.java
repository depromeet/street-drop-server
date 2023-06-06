package unit.annotation;

import com.depromeet.domains.user.entity.User;
import com.depromeet.security.provider.SecurityUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class WithMockCustomAnonymousUserSecurityContextFactory implements WithSecurityContextFactory<MockAnonymousUser> {

    @Override
    public SecurityContext createSecurityContext(MockAnonymousUser annotation) {

        User user = new User();

        SecurityUserDetails userDetails = new SecurityUserDetails(user);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                null
        );
        authentication.setDetails(new WebAuthenticationDetailsSource());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }

}