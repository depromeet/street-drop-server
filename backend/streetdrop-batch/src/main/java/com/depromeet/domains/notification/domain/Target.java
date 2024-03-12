package com.depromeet.domains.notification.domain;

import com.depromeet.domains.notification.domain.vo.Channel;
import lombok.Builder;

@Builder
public class Target {

    private Channel channel;
    private int level;
    private String page;
    private long pageId;

}
