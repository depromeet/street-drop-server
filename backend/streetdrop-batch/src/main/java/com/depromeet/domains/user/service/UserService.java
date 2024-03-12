package com.depromeet.domains.user.service;

import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.level.LevelUpdatePolicy;
import com.depromeet.domains.level.LevelUpdatePolicyImpl;
import com.depromeet.domains.popup.service.PopupService;
import com.depromeet.domains.user.UserItemDto;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

	public static final int LEVEL_ONE = 1;
	public static final int LEVEL_TWO = 2;
	public static final int LEVEL_THREE = 3;

	private final UserRepository userRepository;
	private final UserLevelRepository userLevelRepository;
	private final ItemRepository itemRepository;
	private final Map<Integer, LevelUpdatePolicy> levelUpdatePolicies;
	private final PopupService popupService;

	public UserService(UserRepository userRepository, UserLevelRepository userLevelRepository, ItemRepository itemRepository, PopupService popupService) {
		this.userRepository = userRepository;
		this.userLevelRepository = userLevelRepository;
		this.itemRepository = itemRepository;
		levelUpdatePolicies = createLevelUpdatePolicies();
		this.popupService = popupService;
	}

	private Map<Integer, LevelUpdatePolicy> createLevelUpdatePolicies() {
		Map<Integer, LevelUpdatePolicy> policies = new HashMap<>();
		policies.put(LEVEL_ONE, new LevelUpdatePolicyImpl(0, 4, getUserLevelById(1L)));
		policies.put(LEVEL_TWO, new LevelUpdatePolicyImpl(5, 24, getUserLevelById(2L)));
		policies.put(LEVEL_THREE, new LevelUpdatePolicyImpl(25, 55, getUserLevelById(3L)));
		return policies;
	}

	private UserLevel getUserLevelById(long userLevelId) {
		return userLevelRepository.findUserLevelById(userLevelId);
	}

	@Transactional
	public void updateLevel() {
		List<User> usersToUpdateLevel = new ArrayList<>();
		var allUsers = userRepository.findAll();
		var userIds = allUsers.stream().map(User::getId).collect(Collectors.toList());
		var userItemDtoList = itemRepository.countItemsByUserIdIn(userIds);

		Map<Long, Integer> itemCountMap = userItemDtoList.stream()
				.collect(Collectors.toMap(UserItemDto::getUserId, UserItemDto::getItemCount));

		allUsers.forEach(user -> {
			var itemCount = itemCountMap.getOrDefault(user.getId(), 0);
			updateUserLevel(user, itemCount, usersToUpdateLevel);
		});
		userRepository.saveAll(usersToUpdateLevel);
	}

	private void updateUserLevel(User user, Integer itemCount, List<User> usersToUpdateLevel) {
		for (Map.Entry<Integer, LevelUpdatePolicy> entry : levelUpdatePolicies.entrySet()) {
			LevelUpdatePolicy policy = entry.getValue();
			if (policy.isAcceptable(itemCount)) {
				user.changeLevel(policy.getNewLevel().getId());
				popupService.createLevelUpPopup(user, policy.getNewLevel().getId());
				usersToUpdateLevel.add(user);
				break;
			}
		}
	}
}
