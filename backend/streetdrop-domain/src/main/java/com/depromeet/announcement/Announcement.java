package com.depromeet.announcement;

import static lombok.AccessLevel.PROTECTED;

import com.depromeet.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Announcement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Builder
    public Announcement(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
