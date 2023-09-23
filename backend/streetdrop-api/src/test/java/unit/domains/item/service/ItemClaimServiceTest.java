package unit.domains.item.service;


import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.item.service.ItemClaimService;
import com.depromeet.item.Item;
import com.depromeet.item.ItemClaim;
import com.depromeet.report.dto.ItemClaimReportDto;
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

import java.lang.reflect.Field;
import java.util.Optional;

import static com.depromeet.item.vo.ItemClaimStatus.WAITING;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

        ItemClaim itemClaim;


        @BeforeEach
        void setUp() throws NoSuchFieldException, IllegalAccessException {
            user = User.builder()
                    .nickname("test")
                    .build();

            Field userIdField = User.class.getDeclaredField("id");
            userIdField.setAccessible(true);
            userIdField.set(user, 1L);

            itemClaimRequestDto = new ItemClaimRequestDto(1L, "Bad Item Contents");

            item = Item.builder()
                    .user(user)
                    .content("TEST")
                    .build();

            itemClaim = ItemClaim.builder()
                    .itemId(item.getId())
                    .userId(user.getId())
                    .reason(itemClaimRequestDto.getReason())
                    .status(WAITING)
                    .build();

            Field itemClaimField = ItemClaim.class.getDeclaredField("id");
            itemClaimField.setAccessible(true);
            itemClaimField.set(itemClaim, 1L);

        }

        @Nested
        @DisplayName("성공")
        class Success {

            @DisplayName("아이템 신고 - 성공")
            @Test
            void claimItemSuccess() {
                given(itemRepository.findById(anyLong())).willReturn(Optional.of(item));
                given(itemClaimRepository.existsByUserIdAndItemId(anyLong(), anyLong())).willReturn(false);
                given(itemClaimRepository.save(any(ItemClaim.class))).willReturn(itemClaim);

                itemClaimService.claimItem(user, itemClaimRequestDto);

                verify(slackItemClaimReportService).sendReport(any(ItemClaimReportDto.class));
            }

        }

        @Nested
        @DisplayName("실패")
        class Fail {

        }

    }


}
