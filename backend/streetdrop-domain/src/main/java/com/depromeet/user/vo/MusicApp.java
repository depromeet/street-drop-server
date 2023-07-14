package com.depromeet.user.vo;

public enum MusicApp {
	YOUTUBE_MUSIC("youtubemusic"),
	SPOTIFY("spotify"),
	APPLE_MUSIC("applemusic")
	;

	private final String appName;

	MusicApp(String appName) {
		this.appName = appName;
	}

	public String getAppName() {
		return appName;
	}
}
