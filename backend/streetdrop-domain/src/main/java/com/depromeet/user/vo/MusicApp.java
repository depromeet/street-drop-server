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
		if (appName == null) {
			return YOUTUBE_MUSIC.appName;
		}
		return appName;
	}
}
