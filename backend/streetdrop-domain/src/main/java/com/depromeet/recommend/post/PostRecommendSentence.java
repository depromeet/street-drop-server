package com.depromeet.recommend.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class PostRecommendSentence {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_recommend_sentence_id")
    private Long id;

    @Column(nullable = false)
    private String sentence;

    @Builder
    public PostRecommendSentence(String sentence) {
        this.sentence = sentence;
    }

}
