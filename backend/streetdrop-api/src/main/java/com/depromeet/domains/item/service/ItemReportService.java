package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.domains.item.repository.ItemReportRepository;
import com.depromeet.item.Item;
import com.depromeet.item.ItemReport;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemReportService {
    private final ItemReportRepository itemReportRepository;
    private final ItemService itemService;

    @Transactional
    public void reportItem(User user, Long itemId) {
        var item = itemService.getItem(itemId);
        checkUserAlreadyReport(user, item);

        var itemReport = ItemReport.builder()
                .item(item)
                .user(user)
                .build();
        itemReportRepository.save(itemReport);
    }

    private void checkUserAlreadyReport(User user, Item item) {
        boolean alreadyReported = itemReportRepository.existsByUserIdAndItemId(user.getId(), item.getId());
        if (alreadyReported) {
            throw new BusinessException(ErrorCode.ALREADY_ITEM_REPORTED_ERROR);
        }
    }
}
