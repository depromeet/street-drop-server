package com.depromeet.streetdrop.global.security.provider;

import com.depromeet.streetdrop.domains.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("SecurityUserDetails 테스트")
public class SecurityUserDetailsTest {

    @Test
    @DisplayName("SecurityUserDetails 테스트")
    void testSecurityUserDetailsTest() {
        User user = new User("nickname", null, "idfv");
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(user);
        assertThat(securityUserDetails.getAuthorities()).isNull();
        assertThat(securityUserDetails.getPassword()).isNull();
        assertThat(securityUserDetails.getUsername()).isEqualTo("nickname");
        assertThat(securityUserDetails.isAccountNonExpired()).isFalse();
        assertThat(securityUserDetails.isAccountNonLocked()).isFalse();
        assertThat(securityUserDetails.isCredentialsNonExpired()).isFalse();
        assertThat(securityUserDetails.isEnabled()).isFalse();
    }

}
