package com.streetdrop.report.service;

import com.streetdrop.report.dto.ItemClaimReportDto;

public interface ItemClaimReportService {
    void sendReport(ItemClaimReportDto itemClaimReportDto);
}
