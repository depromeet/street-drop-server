package com.depromeet.report;

import com.depromeet.external.slack.SlackService;
import com.depromeet.item.ItemClaim;
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

    public void sendReport(ItemClaim itemClaim) {
        var markdownTextObject = markdownText("*Reporter:* " + itemClaim.getUser().getNickname()
                + "\n*Item Id:* " + itemClaim.getItem().getId()
                + "\n*Item Content:* " + itemClaim.getItem().getContent()
                + "\n*Report Time:* " + itemClaim.getCreatedAt()
        );

        List<LayoutBlock> blocks = asBlocks(
                header(h -> h.text(plainText(TITLE))),
                section(s -> s.text(markdownTextObject))
        );

        slackService.sendMessage(blocks, slackReportingChannel);
    }

}
