package com.depromeet.domains.recommend.search.repository;

import com.depromeet.recommend.search.SearchRecommendTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRecommendTermRepository extends JpaRepository<SearchRecommendTerm, Long> {
}
