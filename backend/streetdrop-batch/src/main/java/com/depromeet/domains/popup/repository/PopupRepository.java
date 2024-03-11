package com.depromeet.domains.popup.repository;

import com.depromeet.popup.Popup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PopupRepository extends JpaRepository<Popup, Long> {
}
