package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.item.Item;
import com.depromeet.item.ItemReport;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemClaimService {
    private final ItemClaimRepository itemClaimRepository;
    private final ItemService itemService;

    @Transactional
    public void claimItem(User user, Long itemId) {
        var item = itemService.getItem(itemId);
        checkUserAlreadyReport(user, item);

        var itemReport = ItemReport.builder()
                .item(item)
                .user(user)
                .build();
        itemClaimRepository.save(itemReport);
    }

    private void checkUserAlreadyReport(User user, Item item) {
        boolean alreadyReported = itemClaimRepository.existsByUserIdAndItemId(user.getId(), item.getId());
        if (alreadyReported) {
            throw new BusinessException(ErrorCode.ALREADY_ITEM_REPORTED_ERROR);
        }
    }
}