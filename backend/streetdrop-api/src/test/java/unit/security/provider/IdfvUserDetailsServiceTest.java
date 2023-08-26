package unit.security.provider;

import com.streetdrop.domains.user.service.UserService;
import com.streetdrop.security.provider.IdfvUserDetailsService;
import com.streetdrop.security.provider.SecurityUserDetails;
import com.streetdrop.user.User;
import com.streetdrop.user.vo.MusicApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Provider] IdfvUserDetailsService 테스트")
public class IdfvUserDetailsServiceTest {

    @InjectMocks
    IdfvUserDetailsService idfvUserDetailsService;

    @Mock
    UserService userService;

    @DisplayName("유저 IDFV로 조회하거나 생성")
    @Test
    void loadUserByUsernameTest() {
        String idfv = "idfv";
        User user = User.builder()
                .nickname("testUser")
                .idfv("test-idfv")
                .userLevelId(1L)
                .musicApp(MusicApp.YOUTUBE_MUSIC)
                .build();

        SecurityUserDetails securityUserDetails = new SecurityUserDetails(user);
        when(userService.getOrCreateUserByIdfv(any())).thenReturn(user);

        var result = idfvUserDetailsService.loadUserByUsername(idfv);

        assertThat(result).usingRecursiveComparison().isEqualTo(securityUserDetails);
    }

}
