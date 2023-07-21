package com.depromeet.domains.level;

import com.depromeet.user.UserLevel;
import lombok.AllArgsConstructor;

/**
 * 기능 요구 사항
 * 등급    레벨명        조건
 *  1   스트릿드랍퍼   드랍횟수 0~4
 *  2   열정드랍퍼    드랍횟수 5~24
 *  3   스페셜 DJ    드랍횟수 25~55
 */
@AllArgsConstructor
public class LevelUpdatePolicyImpl implements LevelUpdatePolicy {
	private final int minItemDropCount;
	private final int maxItemDropCount;
	private final UserLevel newLevel;

	@Override
	public boolean isAcceptable(int itemCount) {
		return itemCount >= minItemDropCount && itemCount <= maxItemDropCount;
	}

	@Override
	public UserLevel getNewLevel() {
		return newLevel;
	}
}
