package com.depromeet.report;

import com.depromeet.external.slack.SlackService;
import com.depromeet.item.ItemClaim;
import com.slack.api.model.block.LayoutBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
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
        var markdownTextObject = markdownText("*신고자:* " + itemClaim.getUser().getNickname()
                + "\n*아이템 아이디:* " + itemClaim.getItem().getId()
                + "\n*아이템 콘텐츠:* " + itemClaim.getItem().getContent()
                + "\n*신고 시간:* " + itemClaim.getCreatedAt()
        );

        List<LayoutBlock> blocks = asBlocks(
                header(h -> h.text(plainText(TITLE))),
                section(s -> s.text(markdownTextObject))
        );

        slackService.sendMessage(blocks, slackReportingChannel);
    }

}
