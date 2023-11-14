package com.depromeet.report.block.service;

import com.depromeet.external.discord.DiscordService;
import com.depromeet.report.block.dto.UserBlockReportDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscordUserBlockReportService implements UserBlockReportService {

	@Value("${discord.webhook.alert.block.report-channel}")
	private String channel;

	@Value("${discord.webhook.alert.block.url}")
	private String url;

	private final DiscordService discordService;
	private static final String TITLE = "User Block Report";

	@Override
	public void sendReport(UserBlockReportDto userBlockReportDto) {
		var msg = TITLE
				+ "\n*ID:* " + userBlockReportDto.id()
				+ "\n*차단한 사용자 아이디:* " + userBlockReportDto.blockerId()
				+ "\n*차단된 사용자 아이디:* " + userBlockReportDto.blockedId();

		discordService.sendMessages(url, msg, channel);
	}
}
