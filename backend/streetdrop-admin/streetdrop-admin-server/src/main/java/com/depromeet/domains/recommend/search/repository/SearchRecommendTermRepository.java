package com.depromeet.domains.recommend.search.repository;

import com.depromeet.recommend.search.SearchRecommendTerm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchRecommendTermRepository extends JpaRepository<SearchRecommendTerm, Long> {

    @Query(value = "SELECT * FROM search_recommend_term",
            nativeQuery = true,
            countQuery = "SELECT count(s) FROM search_recommend_term s")
    Page<SearchRecommendTerm> findAll(Pageable pageable);
}
