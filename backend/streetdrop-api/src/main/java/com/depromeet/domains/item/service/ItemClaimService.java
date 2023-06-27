package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.item.Item;
import com.depromeet.item.ItemClaim;
import com.depromeet.item.ItemClaimStatus;
import com.depromeet.report.SlackItemClaimService;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.item.ItemClaimStatus.WAITING;

@Service
@RequiredArgsConstructor
public class ItemClaimService {
    private final ItemClaimRepository itemClaimRepository;
    private final ItemService itemService;
    private final SlackItemClaimService slackItemClaimService;

    @Transactional
    public void claimItem(User user, ItemClaimRequestDto itemClaimRequestDto) {
        var item = itemService.getItem(itemClaimRequestDto.getItemId());
        checkUserAlreadyReport(user, item);

        var itemClaim = ItemClaim.builder()
                .item(item)
                .user(user)
                .status(WAITING)
                .build();

        var saved = itemClaimRepository.save(itemClaim);
        slackItemClaimService.sendReport(saved);
    }

    private void checkUserAlreadyReport(User user, Item item) {
        boolean alreadyReported = itemClaimRepository.existsByUserIdAndItemId(user.getId(), item.getId());
        if (alreadyReported) {
            throw new BusinessException(ErrorCode.ALREADY_ITEM_REPORTED_ERROR);
        }
    }
}
