package com.depromeet.report.claim.service;

import com.depromeet.external.discord.DiscordService;
import com.depromeet.report.claim.dto.ItemClaimReportDto;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@RequiredArgsConstructor
public class DiscordItemClaimReportService implements ItemClaimReportService {

	@Value("${discord.webhook.alert.claim.report-channel}")
	private String channel;

	@Value("${discord.webhook.alert.claim.url}")
	private String url;

	private final DiscordService discordService;
	public static final String DISCORD_REPORTING_CHANNEL = "claim";
	private static final String TITLE = "Item Claim Report";

	@Override
	public void sendReport(ItemClaimReportDto itemClaimReportDto) {
		var msg = TITLE
				+ "\n*Item Claim ID:* " + itemClaimReportDto.itemClaimId()
				+ "\n*Item Claim Reason:* " + itemClaimReportDto.itemClaimReason()
				+ "\n*Item Claim Status:* " + itemClaimReportDto.itemClaimStatus()
				+ "\n*Reporter:* " + itemClaimReportDto.reportUserId()
				+ "\n*Item Id:* " + itemClaimReportDto.itemId()
				+ "\n*Item Content:* " + itemClaimReportDto.itemContent()
				+ "\n*Claim Time:* " + itemClaimReportDto.claimTime();

		discordService.sendMessages(url, msg, DISCORD_REPORTING_CHANNEL);
	}
}
