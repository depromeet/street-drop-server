package com.depromeet.notification;

import com.depromeet.notification.vo.Channel;
import lombok.Builder;

@Builder
public class Target {

    private Channel channel;
    private int level;
    private String page;
    private long pageId;

}
