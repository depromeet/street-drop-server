package unit.domains.user.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.user.dto.response.BlockUserResponseDto;
import com.depromeet.domains.user.repository.BlockUserRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.domains.user.service.UserBlockService;
import com.depromeet.user.BlockUser;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] UserBlockService 테스트")
public class UserBlockServiceTest {

    @InjectMocks
    UserBlockService userBlockService;


    @Mock
    UserRepository userRepository;

    @Mock
    BlockUserRepository blockUserRepository;


    @DisplayName("유저 차단")
    @Nested
    class UserBlockTest {

        private long blockUserId;

        private long requestUserId;

        private User requestUser;

        private User blockedUser;

        private BlockUser block;

        private BlockUserResponseDto blockUserResponseDto;

        /*
         * This Method is used to set User's id field.
         */
        private void setUserId(User user, long id) throws NoSuchFieldException, IllegalAccessException {
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(user, id);
        }


        @BeforeEach
        void setUp() throws NoSuchFieldException, IllegalAccessException {
            blockUserId = 2;
            requestUserId = 1;

            requestUser = User.builder()
                    .nickname("RequestUser")
                    .idfv("idfv1")
                    .userLevelId(1L)
                    .musicApp(MusicApp.YOUTUBE_MUSIC)
                    .build();

            blockedUser = User.builder()
                    .nickname("BlockedUser")
                    .idfv("idfv2")
                    .userLevelId(1L)
                    .musicApp(MusicApp.YOUTUBE_MUSIC)
                    .build();

            setUserId(requestUser, requestUserId);
            setUserId(blockedUser, blockUserId);

            block = BlockUser.builder()
                    .blockerId(requestUserId)
                    .blockedId(blockUserId)
                    .build();

            blockUserResponseDto = new BlockUserResponseDto(blockedUser);
        }

        @DisplayName("유저 차단 - 성공")
        @Nested
        class Success {
            @DisplayName("유저 차단 - 성공")
            @Test
            void userBlockTestSuccess() {
                given(userRepository.findUserById(blockUserId)).willReturn(Optional.of(blockedUser));
                given(blockUserRepository.save(any(BlockUser.class))).willReturn(block);

                var response = userBlockService.blockUser(requestUser, blockUserId);

                assertThat(response).isEqualTo(blockUserResponseDto);
                verify(blockUserRepository).save(any());
            }
        }


        @DisplayName("유저 차단 - 실패")
        @Nested
        class Fail {

            @DisplayName("차단하려는 유저가 없는 경우")
            @Test
            void userBlockTestFail_Not_Found_User() {
                given(userRepository.findUserById(anyLong())).willReturn(Optional.empty());

                Throwable thrown = catchThrowable(() -> userBlockService.blockUser(requestUser, blockUserId));

                Assertions.assertThat(thrown).isInstanceOf(NotFoundException.class);
            }

            @DisplayName("차단하려는 유저가 본인인 경우")
            @Test
            void userBlockTestFail_Can_Not_Block_Self() {
                given(userRepository.findUserById(anyLong())).willReturn(Optional.of(requestUser));

                Throwable thrown = catchThrowable(() -> userBlockService.blockUser(requestUser, requestUserId));

                Assertions.assertThat(thrown).isInstanceOf(BusinessException.class)
                        .hasMessageContaining(ErrorCode.CAN_NOT_BLOCK_SELF.getMessage());

            }
        }
    }

    @DisplayName("유저 차단 해제")
    @Nested
    class UserUnBlockTest {


        private long blockUserId;

        private long requestUserId;

        private User requestUser;

        private BlockUser block;


        /*
         * This Method is used to set User's id field.
         */
        private void setUserId(User user, long id) throws NoSuchFieldException, IllegalAccessException {
            Field idField = User.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(user, id);
        }


        @BeforeEach
        void setUp() throws NoSuchFieldException, IllegalAccessException {
            blockUserId = 2;
            requestUserId = 1;

            requestUser = User.builder()
                    .nickname("RequestUser")
                    .idfv("idfv1")
                    .userLevelId(1L)
                    .musicApp(MusicApp.YOUTUBE_MUSIC)
                    .build();

            setUserId(requestUser, requestUserId);

            block = BlockUser.builder()
                    .blockerId(requestUserId)
                    .blockedId(blockUserId)
                    .build();

        }

        @DisplayName("유저 차단 해제 - 성공")
        @Nested
        class Success {
            @DisplayName("유저 차단 해제 - 성공")
            @Test
            void userUnBlockTestSuccess() {
                given(blockUserRepository.findBlockUserByBlockerIdAndBlockedId(requestUserId, blockUserId)).willReturn(Optional.of(block));

                userBlockService.unBlockUser(requestUser, blockUserId);

                verify(blockUserRepository).delete(block);
            }
        }


        @DisplayName("유저 차단 해제 - 실패")
        @Nested
        class Fail {

            @DisplayName("차단 해제하려는 유저가 없는 경우")
            @Test
            void userUnBlockTestFail_Not_Found_User() {
                given(blockUserRepository.findBlockUserByBlockerIdAndBlockedId(anyLong(), anyLong())).willReturn(Optional.empty());

                Throwable thrown = catchThrowable(() -> userBlockService.unBlockUser(requestUser, blockUserId));

                Assertions.assertThat(thrown).isInstanceOf(NotFoundException.class);
            }

        }
    }
}
