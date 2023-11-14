package com.depromeet.external.discord;

import lombok.NonNull;

public record DiscordWebhookPayload(
		@NonNull String content
) {
}