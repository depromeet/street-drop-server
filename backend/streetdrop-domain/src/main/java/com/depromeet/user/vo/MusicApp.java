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

	public static MusicApp findByAppName(String appName) {
		return Arrays.stream(MusicApp.values())
				.filter(musicApp -> musicApp.appName.equals(appName))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("This is not support music app. " +
						"Support music apps are: " + Arrays.stream(MusicApp.values())
						.map(MusicApp::getAppName)
						.collect(Collectors.joining(", "))));
	}
}
