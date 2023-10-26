package com.depromeet.external.discord;

public class DiscordWebhookPayload {
	private final String content;

	public DiscordWebhookPayload(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
