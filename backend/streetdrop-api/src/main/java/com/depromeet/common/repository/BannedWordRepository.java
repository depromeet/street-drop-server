package com.depromeet.common.repository;

import com.depromeet.common.entity.BannedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BannedWordRepository extends JpaRepository<BannedWord, Long> {

    @Query("SELECT bw.word FROM BannedWord bw WHERE bw.word IN :words LIMIT 1")
    List<String> findBannedWordsInWordList(@Param("words") List<String> words);

}
