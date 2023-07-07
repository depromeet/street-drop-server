package com.depromeet.domain;

import com.depromeet.common.domain.BaseTimeEntity;
import com.depromeet.domain.vo.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.reflect.Parameter;
import java.util.List;

@AllArgsConstructor
@Getter
@Document("target")
public class Target extends BaseTimeEntity {

    private Channel channel;
    private int level;
    private String page;
    private long pageId;
    private List<Parameter> parameter;

}
