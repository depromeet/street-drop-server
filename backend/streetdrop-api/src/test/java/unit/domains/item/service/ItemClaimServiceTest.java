package unit.domains.item.service;


import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.item.service.ItemClaimService;
import com.depromeet.item.Item;
import com.depromeet.report.service.SlackItemClaimReportService;
import com.depromeet.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] ItemClaimService 테스트")
public class ItemClaimServiceTest {

    @InjectMocks
    ItemClaimService itemClaimService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    ItemClaimRepository itemClaimRepository;

    @Mock
    SlackItemClaimReportService slackItemClaimReportService;

    @DisplayName("아이템 신고")
    @Nested
    class ClaimItemTest {

        User user;

        ItemClaimRequestDto itemClaimRequestDto;

        Item item;


        @BeforeEach
        void setUp() {
            user = User.builder()
                    .nickname("test")
                    .build();

            itemClaimRequestDto = new ItemClaimRequestDto(1L, "Bad Item Contents");

            item = Item.builder()
                    .user(user)
                    .content("TEST")
                    .build();
        }

        @Nested
        @DisplayName("성공")
        class Success {

            @DisplayName("아이템 신고 - 성공")
            @Test
            void claimItemSuccess() {
                // Given
                given(itemRepository.findById(anyLong())).willReturn(Optional.of(item));
                given(itemClaimRepository.existsByUserIdAndItemId(anyLong(), anyLong())).willReturn(false);

                // When

                // Then
            }

        }

        @Nested
        @DisplayName("실패")
        class Fail {

        }

    }


}
