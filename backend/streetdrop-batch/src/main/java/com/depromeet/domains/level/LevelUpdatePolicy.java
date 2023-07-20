package com.depromeet.domains.level;

import com.depromeet.user.UserLevel;

/**
 * 기능 요구 사항
 * 등급    레벨명        조건
 *  1   스트릿드랍퍼   드랍횟수 0~4
 *  2   열정드랍퍼    드랍횟수 5~24
 *  3   스페셜 DJ    드랍횟수 25~54
 */
public interface LevelUpdatePolicy {
	boolean isAcceptable(int itemCount);
	UserLevel getNewLevel();
}