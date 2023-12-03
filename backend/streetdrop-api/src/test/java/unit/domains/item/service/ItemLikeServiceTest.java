package unit.domains.item.service;

import com.depromeet.common.error.exception.internal.BusinessException;
import com.depromeet.common.error.exception.internal.NotFoundException;
import com.depromeet.domains.item.dto.response.ItemLikeResponseDto;
import com.depromeet.domains.item.error.ItemErrorCode;
import com.depromeet.domains.item.repository.ItemLikeRepository;
import com.depromeet.domains.item.service.ItemLikeService;
import com.depromeet.domains.item.service.ItemService;
import com.depromeet.item.Item;
import com.depromeet.item.ItemLike;
import com.depromeet.user.User;
import com.depromeet.user.vo.MusicApp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("[Service] ItemLikeService 테스트")
public class ItemLikeServiceTest {

	@InjectMocks
	private ItemLikeService itemLikeService;

	@Mock
	private ItemService itemService;

	@Mock
	private ItemLikeRepository itemLikeRepository;

	@DisplayName("아이템 좋아요")
	@Nested
	class ItemLikeTest {

		@Nested
		@DisplayName("성공")
		class Success {

			@Test
			@DisplayName("좋아요 - 성공")
			void likeItemSuccess() {
				// Given
				User user = User.builder()
						.nickname("test1")
						.idfv("1234567")
						.musicApp(MusicApp.APPLE_MUSIC)
						.build();
				long itemId = 1L;

				Item item = Item.builder()
						.user(user)
						.content("TEST")
						.build();

				ItemLike itemLike = ItemLike.builder()
						.item(item)
						.user(user)
						.build();

				given(itemService.getItem(itemId)).willReturn(item);
				given(itemLikeRepository.existsByUserIdAndItemId(user.getId(), item.getId())).willReturn(false);
				given(itemLikeRepository.save(any(ItemLike.class))).willReturn(itemLike);

				// When
				ItemLikeResponseDto result = itemLikeService.likeItem(user, itemId);

				// Then
				assertThat(result).isNotNull();
			}
		}

		@Nested
		@DisplayName("실패")
		class Fail {

			@Test
			@DisplayName("좋아요 실패 - 이미 좋아요한 아이템인 경우")
			void likeItemAlreadyLiked() {
				// Given
				User user = User.builder()
						.nickname("test1")
						.idfv("1234567")
						.build();
				long itemId = 1L;

				Item item = Item.builder()
						.user(user)
						.content("TEST")
						.build();

				given(itemService.getItem(itemId)).willReturn(item);
				given(itemLikeRepository.existsByUserIdAndItemId(user.getId(), item.getId())).willReturn(true);

				// When
				Throwable thrown = catchThrowable(() -> itemLikeService.likeItem(user, itemId));

				// Then
				assertThat(thrown).isInstanceOf(BusinessException.class)
						.hasMessageContaining(ItemErrorCode.ITEM_ALREADY_LIKED.getMessage());
			}

			@Test
			@DisplayName("좋아요 실패 - 아이템을 찾을 수 없는 경우")
			void likeItemItemNotFound() {
				// Given
				User user = User.builder()
						.nickname("test1")
						.idfv("1234567")
						.build();
				long itemId = 1L;

				given(itemService.getItem(itemId)).willThrow(NotFoundException.class);

				// When
				Throwable thrown = catchThrowable(() -> itemLikeService.likeItem(user, itemId));

				// Then
				assertThat(thrown).isInstanceOf(NotFoundException.class);
			}
		}
	}

	@Nested
	@DisplayName("아이템 좋아요 취소")
	class unlikeTest {
		@Nested
		@DisplayName("성공")
		class Success {
			@Test
			@DisplayName("좋아요 취소 - 취소 성공")
			void unlikeItemSuccess() {
				// Given
				User user = User.builder()
						.nickname("test1")
						.idfv("1234567")
						.build();
				long itemId = 1L;

				Item item = Item.builder()
						.user(user)
						.content("TEST")
						.build();

				ItemLike itemLike = ItemLike.builder()
						.item(item)
						.user(user)
						.build();

				given(itemLikeRepository.findByItemIdAndUser(itemId, user)).willReturn(Optional.of(itemLike));

				// When
				itemLikeService.unlikeItem(user, itemId);

				// Then
				verify(itemLikeRepository).delete(itemLike);
			}
		}
	}
}
