package com.depromeet.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class MemberLoginLog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Setter
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String loginIp;

    @Column(nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String loginResult;


}
