package com.depromeet.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
@Table(indexes = {
        @Index(name = "idx__banned_word_word", columnList = "word")
})
public class BannedWord {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "banned_word_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String word;
}