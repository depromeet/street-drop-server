package com.depromeet.streetdrop.domains.music.genre.repository;

import com.depromeet.streetdrop.domains.music.genre.entity.SongGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongGenreRepository extends JpaRepository<SongGenre, Long> {
}
