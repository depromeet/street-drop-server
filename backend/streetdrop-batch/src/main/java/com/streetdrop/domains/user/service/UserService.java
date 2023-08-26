package com.streetdrop.domains.user.service;

import com.streetdrop.domains.item.repository.ItemRepository;
import com.streetdrop.domains.level.LevelUpdatePolicy;
import com.streetdrop.domains.level.LevelUpdatePolicyImpl;
import com.streetdrop.domains.user.UserItemDto;
import com.streetdrop.domains.user.repository.UserLevelRepository;
import com.streetdrop.domains.user.repository.UserRepository;
import com.streetdrop.user.User;
import com.streetdrop.user.UserLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

	public UserService(UserRepository userRepository, UserLevelRepository userLevelRepository, ItemRepository itemRepository) {
		this.userRepository = userRepository;
		this.userLevelRepository = userLevelRepository;
		this.itemRepository = itemRepository;
		levelUpdatePolicies = createLevelUpdatePolicies();
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
				usersToUpdateLevel.add(user);
				break;
			}
		}
	}
}
