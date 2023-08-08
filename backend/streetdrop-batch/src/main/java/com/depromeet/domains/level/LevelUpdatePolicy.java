package com.depromeet.domains.level;

import com.depromeet.user.UserLevel;

public interface LevelUpdatePolicy {
	boolean isAcceptable(int itemCount);
	UserLevel getNewLevel();
}