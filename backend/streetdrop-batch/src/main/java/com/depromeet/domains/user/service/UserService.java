package com.depromeet.domains.user.service;

import com.depromeet.domains.level.LevelUpdatePolicy;
import com.depromeet.domains.level.LevelUpdatePolicyImpl;
import com.depromeet.domains.user.repository.UserLevelRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.user.User;
import com.depromeet.user.UserLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserService {

	private final UserRepository userRepository;

	private final UserLevelRepository userLevelRepository;

	private final List<LevelUpdatePolicy> levelUpdatePolicies;

	public UserService(UserRepository userRepository, UserLevelRepository userLevelRepository) {
		this.userRepository = userRepository;
		this.userLevelRepository = userLevelRepository;
		levelUpdatePolicies = Arrays.asList(
				new LevelUpdatePolicyImpl(5, getUserLevelById(userLevelRepository, 2L)),
				new LevelUpdatePolicyImpl(25, getUserLevelById(userLevelRepository, 3L))
		);
	}

	private UserLevel getUserLevelById(UserLevelRepository userLevelRepository, long userLevelId) {
		return userLevelRepository.findUserLevelById(userLevelId);
	}

	@Transactional
	public void updateLevel() {
		var usersToUpdateLevel = findUsersToUpdateLevel();
		updateUsersLevel(usersToUpdateLevel);
	}

	private List<User> findUsersToUpdateLevel() {
		List<User> usersToUpdateLevel = new ArrayList<>();
		List<User> allUsers = userRepository.findAll();

		for (User user : allUsers) {
			var itemCount = getItemCount(user);
			for (LevelUpdatePolicy policy : levelUpdatePolicies) {
				if (policy.isAcceptable(itemCount)) {
					var newLevel = policy.getNewLevel();
					user.changeLevel(newLevel.getId());
					usersToUpdateLevel.add(user);
				}
			}
		}
		return usersToUpdateLevel;
	}

	private int getItemCount(User user) {
		return user.getItems() != null ? user.getItems().size() : 0;
	}

	private void updateUsersLevel(List<User> usersToUpdateLevel) {
		userRepository.saveAll(usersToUpdateLevel);
	}
}
