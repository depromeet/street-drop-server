package com.depromeet.common.repository;

import com.depromeet.common.entity.BannedWord;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BannedWordRepository extends JpaRepository<BannedWord, Long> {

    @Query("SELECT bw.word FROM BannedWord bw WHERE bw.word IN :words LIMIT 1")
    Optional<String> findBannedWordsInWordList(@Param("words") List<String> words);

}