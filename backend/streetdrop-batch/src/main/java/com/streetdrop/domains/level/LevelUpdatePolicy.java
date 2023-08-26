package com.streetdrop.domains.level;

import com.streetdrop.user.UserLevel;

public interface LevelUpdatePolicy {
	boolean isAcceptable(int itemCount);
	UserLevel getNewLevel();
}