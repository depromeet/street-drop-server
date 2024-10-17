package com.depromeet.domains.recommend.repository;

import com.depromeet.recommend.post.PostRecommendSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRecommendSentenceRepository extends JpaRepository<PostRecommendSentence, Long> {
}
