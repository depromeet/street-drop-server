package com.depromeet.vo;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor
public class SaveTokenMessage {
    private Long userId;

    private String token;
}
