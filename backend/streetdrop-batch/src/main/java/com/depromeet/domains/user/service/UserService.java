package com.depromeet.domains.user.service;

import com.depromeet.domains.item.dto.UserItemCount;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.level.LevelUpdater;
import com.depromeet.domains.popup.service.PopupService;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.level.data.LevelPolicy;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final ItemRepository itemRepository;
	private final PopupService popupService;

	@Transactional
	public void updateLevel() {
		var itemUpdateUsers = itemRepository.findAllItemUpdateUser(LocalDateTime.now().minusMinutes(30)).stream().distinct().toList();

		var userIds = itemUpdateUsers.stream().map(User::getId).toList();
		List<UserItemCount> userItemDtoMap = itemRepository.countItemsByUserIdIn(userIds);


		itemUpdateUsers.forEach(
				user -> {
					var userId = user.getId();
					var itemCount = userItemDtoMap.stream()
							.filter(dto -> dto.getUserId().equals(userId))
							.map(UserItemCount::getItemCount)
							.findFirst()
							.orElse(null);

					LevelPolicy newLevel = LevelUpdater.getUpdateLevel(itemCount);
					if (newLevel.getLevel() > user.getUserLevel().getId()) {
						user.changeLevel(newLevel.getLevel());
						popupService.createLevelUpPopup(user, newLevel.getLevel());
					}
				}
		);

		userRepository.saveAll(itemUpdateUsers);
	}
}
