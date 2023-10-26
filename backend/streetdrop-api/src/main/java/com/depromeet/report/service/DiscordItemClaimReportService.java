package com.depromeet.report.service;

import com.depromeet.external.discord.DiscordService;
import com.depromeet.report.dto.ItemClaimReportDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DiscordItemClaimReportService implements ItemClaimReportService {

	public static final String DISCORD_REPORTING_CHANNEL = "claim";
	private DiscordService discordService;

	@Value("${discord.webhook.alert.report-channel}")
	private String discordReportingChannel;

	@Value("${discord.webhook.alert.url}")
	private String url;

	private static final String TITLE = "Item Claim Report";


	@Override
	public void sendReport(ItemClaimReportDto itemClaimReportDto) {
		var msg = "Item Claim ID:* " + itemClaimReportDto.itemClaimId()
				+ "\n*Item Claim Reason:* " + itemClaimReportDto.itemClaimReason()
				+ "\n*Item Claim Status:* " + itemClaimReportDto.itemClaimStatus()
				+ "\n*Reporter:* " + itemClaimReportDto.reportUserId()
				+ "\n*Item Id:* " + itemClaimReportDto.itemId()
				+ "\n*Item Content:* " + itemClaimReportDto.itemContent()
				+ "\n*Claim Time:* " + itemClaimReportDto.claimTime();

		discordService.sendMessages(url, msg, DISCORD_REPORTING_CHANNEL);
	}
}
