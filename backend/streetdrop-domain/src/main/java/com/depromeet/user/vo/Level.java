package com.depromeet.user.vo;

public enum Level {
	DROP_STARTER(1, "L.1 드랍 스타터", "당신은 드랍 스타터 입니다. 드랍을 시작해보세요!"),
	DROP_FEVER(2, "L.2 열정 드라퍼", "당신은 열정적인 드라퍼 입니다."),
	SPECIAL_DJ(3, "L.3 스페셜 DJ", "당신은 스페셜 DJ 입니다.");

	private final int levelRank;
	private final String levelName;
	private final String levelDescription;

	Level(int levelRank, String levelName, String levelDescription) {
		this.levelRank = levelRank;
		this.levelName = levelName;
		this.levelDescription = levelDescription;
	}

	public int getLevelRank() {
		return levelRank;
	}

	public String getLevelName() {
		return levelName;
	}

	public String getLevelDescription() {
		return levelDescription;
	}
}
