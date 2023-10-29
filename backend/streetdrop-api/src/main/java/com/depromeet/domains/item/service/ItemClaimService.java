package com.depromeet.domains.item.service;

import com.depromeet.common.error.dto.ErrorCode;
import com.depromeet.common.error.exception.common.BusinessException;
import com.depromeet.common.error.exception.common.NotFoundException;
import com.depromeet.domains.item.dto.request.ItemClaimRequestDto;
import com.depromeet.domains.item.repository.ItemClaimRepository;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.item.ItemClaim;
import com.depromeet.report.claim.dto.ItemClaimReportDto;
import com.depromeet.report.claim.service.DiscordItemClaimReportService;
import com.depromeet.report.claim.service.SlackItemClaimReportService;
import com.depromeet.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.depromeet.item.vo.ItemClaimStatus.WAITING;

@Service
@RequiredArgsConstructor
public class ItemClaimService {
    private final ItemClaimRepository itemClaimRepository;
    private final ItemRepository itemRepository;
    private final SlackItemClaimReportService slackItemClaimReportService;
    private final DiscordItemClaimReportService discordItemClaimReportService;

    @Transactional
    public void claimItem(User user, ItemClaimRequestDto itemClaimRequestDto) {
        var itemId = itemClaimRequestDto.getItemId();
        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND, String.valueOf(itemId)));
        checkUserAlreadyReport(user.getId(), itemId);

        var itemClaim = ItemClaim.builder()
                .reason(itemClaimRequestDto.getReason())
                .itemId(itemId)
                .userId(user.getId())
                .status(WAITING)
                .build();

        var saveditemClaim = itemClaimRepository.save(itemClaim);

        ItemClaimReportDto itemClaimReportDto = ItemClaimReportDto.builder()
                .itemClaimId(saveditemClaim.getId())
                .itemClaimReason(saveditemClaim.getReason())
                .itemClaimStatus(saveditemClaim.getStatus())
                .reportUserId(saveditemClaim.getUserId())
                .itemId(saveditemClaim.getItemId())
                .itemContent(item.getContent())
                .build();

        /**
         * Slack에서 Discord로 변경. Slack으로 복귀 시 주석 해제
         */
//        slackItemClaimReportService.sendReport(itemClaimReportDto);
        discordItemClaimReportService.sendReport(itemClaimReportDto);
    }

    private void checkUserAlreadyReport(Long userId, Long itemId) {
        boolean alreadyReported = itemClaimRepository.existsByUserIdAndItemId(userId, itemId);
        if (alreadyReported) {
            throw new BusinessException(ErrorCode.ALREADY_ITEM_REPORTED_ERROR);
        }
    }
}
