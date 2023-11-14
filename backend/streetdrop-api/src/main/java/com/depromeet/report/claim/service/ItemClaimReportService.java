package com.depromeet.report.claim.service;

import com.depromeet.report.claim.dto.ItemClaimReportDto;

public interface ItemClaimReportService {
    void sendReport(ItemClaimReportDto itemClaimReportDto);
}
