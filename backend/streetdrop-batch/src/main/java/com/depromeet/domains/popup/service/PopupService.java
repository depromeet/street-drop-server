package com.depromeet.domains.popup.service;

import com.depromeet.domains.popup.repository.PopupRepository;
import com.depromeet.popup.Popup;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PopupService {

    private final PopupRepository popupRepository;

    public void createLevelUpPopup(User user, long newLevel) {
        Popup popup = new Popup(user.getId(), "LEVEL_" + newLevel);
        popupRepository.save(popup);
    }
}
