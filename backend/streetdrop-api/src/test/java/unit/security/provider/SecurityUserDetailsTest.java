package unit.security.provider;

import com.depromeet.user.User;
import com.depromeet.security.provider.SecurityUserDetails;
import com.depromeet.user.vo.MusicApp;
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

        User user = User.builder()
                .nickname("testUser")
                .userLevelId(1L)
                .musicApp(MusicApp.YOUTUBE_MUSIC)
                .build();
        SecurityUserDetails securityUserDetails = new SecurityUserDetails(user);
        assertThat(securityUserDetails.getAuthorities()).isNull();
        assertThat(securityUserDetails.getPassword()).isNull();
        assertThat(securityUserDetails.getUsername()).isEqualTo("testUser");
        assertThat(securityUserDetails.isAccountNonExpired()).isFalse();
        assertThat(securityUserDetails.isAccountNonLocked()).isFalse();
        assertThat(securityUserDetails.isCredentialsNonExpired()).isFalse();
        assertThat(securityUserDetails.isEnabled()).isFalse();
    }

}
