package com.depromeet.common.monitoring;

import com.depromeet.infra.slack.SlackService;
import com.slack.api.model.block.LayoutBlock;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.markdownText;
import static com.slack.api.model.block.composition.BlockCompositions.plainText;

@RequiredArgsConstructor
@Service
public class SlackMonitoringProvider implements MonitoringProvider {

    private final SlackService slackService;

    @Value("${slack.monitoring-channel}")
    private String slackMonitoringChannel;

    @Async
    public void sendError(String title, HttpServletRequest request, Exception e) {
        ErrorInfo errorInfo = new ErrorInfo().fromException(e);
        RequestInfo requestInfo = new RequestInfo().fromRequest(request);


        var markdownTextObject = markdownText("*에러 내용:* " + errorInfo.errorContent
                + "\n*에러 발생 시간:* " + errorInfo.errorTime
                + "\n*요청 URL:* " + requestInfo.requestUrl
                + "\n*요청 METHOD:* " + requestInfo.requestMethod
                + "\n*에러 원인:* " + errorInfo.errorCause
                + "\n---"
                + "\n*요청 헤더:* ```" + requestInfo.requestHeaders + "```"
                + "\n*에러 스택:* ```" + errorInfo.errorStack + "```"
        );


        List<LayoutBlock> blocks = asBlocks(
                header(h -> h.text(plainText(title))),
                section(s -> s.text(markdownTextObject))
        );

        slackService.sendMessage(blocks, slackMonitoringChannel);
    }

    private static class ErrorInfo {
        String errorContent;
        String errorCause;
        String errorTime;
        String errorStack;

        public ErrorInfo fromException(Exception e) {
            this.errorContent = e.getMessage();
            this.errorCause = (e.getCause() != null) ? e.getCause().toString() : "Cannot find error cause";
            this.errorTime = LocalDateTime.now().toString();
            this.errorStack = Arrays.stream(e.getStackTrace())
                    .limit(10)
                    .map(StackTraceElement::toString)
                    .reduce("", (a, b) -> a + "\n" + b);
            return this;
        }

    }

    private static class RequestInfo {
        String requestUrl;
        String requestMethod;
        String requestQuery;
        String requestHeaders;

        public RequestInfo fromRequest(HttpServletRequest request) {
            this.requestUrl = request.getRequestURL().toString();
            this.requestMethod = request.getMethod();
            this.requestQuery = request.getQueryString();
            var requestHeaderNames = request.getHeaderNames();
            List<String> headerInfo= new ArrayList<>();
            while (requestHeaderNames.hasMoreElements()) {
                String headerName = requestHeaderNames.nextElement();
                String headerValue = request.getHeader(headerName);
                headerInfo.add(headerName + ": " + headerValue);
            }
            this.requestHeaders = String.join("\n", headerInfo);
            return this;
        }
    }


}
