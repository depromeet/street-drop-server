package com.streetdrop.report.service;

import com.streetdrop.external.slack.SlackService;
import com.streetdrop.report.dto.ItemClaimReportDto;
import com.slack.api.model.block.LayoutBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@Component
@RequiredArgsConstructor
public class SlackItemClaimReportService implements ItemClaimReportService {

    private final SlackService slackService;
    @Value("${slack.report-channel}")
    private String slackReportingChannel;
    private static final String TITLE = "Item Claim Report";

    public void sendReport(ItemClaimReportDto itemClaimReportDto) {
        var markdownTextObject = markdownText("*Item Claim Id:* " + itemClaimReportDto.itemClaimId()
                + "\n*Item Claim Reason:* " + itemClaimReportDto.itemClaimReason()
                + "\n*Item Claim Status:* " + itemClaimReportDto.itemClaimStatus()
                + "\n*Reporter:* " + itemClaimReportDto.reportUserId()
                + "\n*Item Id:* " + itemClaimReportDto.itemId()
                + "\n*Item Content:* " + itemClaimReportDto.itemContent()
                + "\n*Claim Time:* " + itemClaimReportDto.claimTime()
        );

        List<LayoutBlock> blocks = asBlocks(
                header(h -> h.text(plainText(TITLE))),
                section(s -> s.text(markdownTextObject))
        );

        slackService.sendMessage(blocks, slackReportingChannel);
    }

}
