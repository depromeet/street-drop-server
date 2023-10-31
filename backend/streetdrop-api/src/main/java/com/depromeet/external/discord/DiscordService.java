package com.depromeet.external.discord;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordService {

	public void sendMessages(String webhookUrl, String content, String discordReportingChannel) {
		try {
			DiscordWebhookPayload payload = new DiscordWebhookPayload(content);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForEntity(webhookUrl, payload, Void.class);
		} catch (Exception e) {
			log.error("Failed to send a Discord message: {}", e.getMessage());
		}
	}
}
