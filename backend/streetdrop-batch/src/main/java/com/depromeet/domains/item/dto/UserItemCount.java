package com.depromeet.domains.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserItemCount {
    private Long userId;
    private Long itemCount;
}
