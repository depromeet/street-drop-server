package com.depromeet.domains.recommend.repository;

import com.depromeet.recommend.search.SearchRecommendTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SearchRecommendTermRepository extends JpaRepository<SearchRecommendTerm, Long> {
    @Query(value = "SELECT s FROM SearchRecommendTerm s WHERE s.active = true ORDER BY RAND() LIMIT 1")
    Optional<SearchRecommendTerm> getRandomSearchRecommendTerm();
}
