package com.depromeet.external.slack;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.model.block.LayoutBlock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class SlackService {

    @Value("${slack.token}")
    private String token;
    @Async
    public void sendMessage(List<LayoutBlock> blocks, String channel) {
        try {
            MethodsClient methods = Slack.getInstance().methods(token);
            ChatPostMessageRequest req = ChatPostMessageRequest.builder()
                    .channel(channel)
                    .blocks(blocks)
                    .build();
            methods.chatPostMessage(req);
        } catch (IOException | SlackApiException e) {
            log.error("Failed to send a Slack message: {}", e.getMessage());
        }
    }
}
