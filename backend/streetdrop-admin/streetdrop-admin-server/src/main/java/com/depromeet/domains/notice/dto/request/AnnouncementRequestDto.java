package com.depromeet.domains.notice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnnouncementRequestDto {

    private String title;

    private String content;

}
