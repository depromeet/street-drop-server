package com.depromeet.domains.dashboard.controller;

import com.depromeet.domains.dashboard.service.DashboardService;
import com.depromeet.common.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/indicators")
    public ResponseEntity<?> getDashboardIndicators() {
        var response = dashboardService.getDashboardIndicators();
        return ResponseDto.ok(response);
    }

    @GetMapping("/reports")
    public ResponseEntity<?> getDashboardReports() {
        var response = dashboardService.getRecentReports();
        return ResponseDto.ok(response);
    }

    @GetMapping("/versions")
    public ResponseEntity<?> getDashboardVersions() {
        var response = dashboardService.getRecentVersions();
        return ResponseDto.ok(response);
    }

    @GetMapping("/appstore-indicators")
    public ResponseEntity<?> getAppstoreIndicators() {
        var response = dashboardService.getAppstoreIndicators();
        return ResponseDto.ok(response);
    }

    @GetMapping("/regional-analysis")
    public ResponseEntity<?> getRegionalAnalysis() {
        var response = dashboardService.getRegionalAnalysis();
        return ResponseDto.ok(response);
    }
}
