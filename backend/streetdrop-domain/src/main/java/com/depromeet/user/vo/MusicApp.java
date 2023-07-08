package com.depromeet.user.vo;

import lombok.Getter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum MusicApp {
	YOUTUBE_MUSIC("youtubemusic"),
	SPOTIFY("spotify"),
	APPLE_MUSIC("applemusic")
	;

	private final String appName;

	MusicApp(String appName) {
		this.appName = appName;
	}
}
