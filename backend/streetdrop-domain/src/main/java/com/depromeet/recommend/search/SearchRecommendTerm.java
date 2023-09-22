package com.depromeet.recommend.search;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class SearchRecommendTerm {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "search_recommend_term_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "json")
    @Type(value = JsonType.class)
    private List<TextColorVo> description;

    @Column(nullable = false, columnDefinition = "json")
    @Type(value = JsonType.class)
    private List<TextColorVo> terms;

    @Column(nullable = false)
    private boolean active;

    public SearchRecommendTerm(String title, List<TextColorVo> description, List<TextColorVo> terms) {
        this.title = title;
        this.description = description;
        this.terms = terms;
        this.active = true;
    }
}
