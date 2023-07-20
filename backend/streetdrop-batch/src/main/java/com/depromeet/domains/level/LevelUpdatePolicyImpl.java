package com.depromeet.domains.level;

import com.depromeet.user.UserLevel;

public class LevelUpdatePolicyImpl implements LevelUpdatePolicy {
	private final int levelCondition;
	private final UserLevel newLevel;

	public LevelUpdatePolicyImpl(int levelCondition, UserLevel userLevel) {
		this.levelCondition = levelCondition;
		this.newLevel = userLevel;
	}

	@Override
	public boolean isAcceptable(int itemCount) {
		return itemCount >= levelCondition;
	}

	@Override
	public UserLevel getNewLevel() {
		return newLevel;
	}
}
