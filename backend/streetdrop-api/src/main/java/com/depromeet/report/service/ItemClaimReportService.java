package com.depromeet.report.service;

import com.depromeet.report.dto.ItemClaimReportDto;

public interface ItemClaimReportService {
    void sendReport(ItemClaimReportDto itemClaimReportDto);
}
