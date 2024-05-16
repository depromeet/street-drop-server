package com.depromeet.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Document(collection = "login_log")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class MemberLoginLog {

    @Id
    private String id;


    private String loginIp;


    private String userAgent;


    private LocalDateTime createdAt;


    private String loginResult;

    private Long memberId;

    private String username;
}
