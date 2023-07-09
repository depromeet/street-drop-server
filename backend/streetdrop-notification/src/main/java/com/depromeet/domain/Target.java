package com.depromeet.domain;

import com.depromeet.domain.vo.Channel;
import lombok.Builder;

@Builder
public class Target {

    private Channel channel;
    private int level;
    private String page;
    private long pageId;

}
