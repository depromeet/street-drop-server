package com.depromeet.domains.popup.repository;

import com.depromeet.popup.Popup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PopupRepository extends JpaRepository<Popup, Long> {
    @Query("SELECT p FROM Popup p WHERE p.isRead = false AND p.userId = :id")
    List<Popup> findAllByUserId(Long id);
}
