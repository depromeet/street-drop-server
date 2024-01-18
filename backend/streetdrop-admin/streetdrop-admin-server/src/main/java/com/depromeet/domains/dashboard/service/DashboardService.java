package com.depromeet.domains.dashboard.service;

import com.depromeet.domains.dashboard.dto.IndicatorResponseDto;
import com.depromeet.domains.dashboard.dto.RegionalAnalysisResponseDto;
import com.depromeet.domains.dashboard.dto.ReportResponseDto;
import com.depromeet.domains.dashboard.dto.VersionResponseDto;
import com.depromeet.domains.item.repository.ItemRepository;
import com.depromeet.domains.user.repository.UserRepository;
import com.depromeet.domains.village.repository.VillageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DashboardService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final VillageRepository villageRepository;

    public List<IndicatorResponseDto> getDashboardIndicators() {


        var allItemCount = IndicatorResponseDto.builder()
                .title("전체 드랍 개수")
                .value(String.valueOf(itemRepository.count()))
                .build();

        var allUserCount = IndicatorResponseDto.builder()
                .title("전체 가입 유저")
                .value(String.valueOf(userRepository.count()))
                .build();

        var wau = IndicatorResponseDto.builder()
                .title("WAU")
                .value("지원예정")
                .build();

        var mau = IndicatorResponseDto.builder()
                .title("MAU")
                .value("지원예정")
                .build();

        var recentEventCount = IndicatorResponseDto.builder()
                .title("최근 이벤트 수")
                .value("지원예정")
                .build();
        var recentActiveUser = IndicatorResponseDto.builder()
                .title("최근 활동 유저")
                .value("지원예정")
                .build();

        return List.of(
                allUserCount,
                allItemCount,
                wau,
                mau,
                recentEventCount,
                recentActiveUser
        );
    }


    public List<ReportResponseDto> getRecentReports() {

        var report1 = ReportResponseDto.builder()
                .id(1L)
                .title("금칙어 사용")
                .content("지금 이 내용은 금칙어가 포함되어 있습니다.")
                .status("Open")
                .build();

        var report2 = ReportResponseDto.builder()
                .id(2L)
                .title("거짓정보")
                .content("거짓정보가 포함된 게시글 입니다")
                .status("Close")
                .build();

        var report3 = ReportResponseDto.builder()
                .id(3L)
                .title("폭력 또는 혐오")
                .content("폭력 또는 혐오가 포함된 게시글 입니다")
                .status("Close")
                .build();

        return List.of(
                report1,
                report2,
                report3
        );

    }

    public List<VersionResponseDto> getRecentVersions() {
        var version1 = VersionResponseDto.builder()
                .id(1L)
                .version("1.0.8")
                .count("124")
                .percentage("60")
                .build();

        var version2 = VersionResponseDto.builder()
                .id(2L)
                .version("1.0.7")
                .count("124")
                .percentage("60")
                .build();

        var version3 = VersionResponseDto.builder()
                .id(3L)
                .version("1.0.6")
                .count("15")
                .percentage("4")
                .build();

        var version4 = VersionResponseDto.builder()
                .id(4L)
                .version("1.0.5")
                .count("14")
                .percentage("14")
                .build();

        var version5 = VersionResponseDto.builder()
                .id(5L)
                .version("1.0.4")
                .count("124")
                .percentage("60")
                .build();

        var version6 = VersionResponseDto.builder()
                .id(6L)
                .version("1.0.3")
                .count("124")
                .percentage("60")
                .build();

        return List.of(
                version1,
                version2,
                version3,
                version4,
                version5,
                version6
        );

    }

    public List<IndicatorResponseDto> getAppstoreIndicators() {

        var value1 = IndicatorResponseDto.builder()
                .title("노출수")
                .value("789")
                .build();

        var value2 = IndicatorResponseDto.builder()
                .title("전환율")
                .value("7.62%")
                .build();

        var value3 = IndicatorResponseDto.builder()
                .title("활성 기기당 세션 비율")
                .value("5.1")
                .build();

        return List.of(
                value1,
                value2,
                value3
        );

    }

    public List<RegionalAnalysisResponseDto> getRegionalAnalysis() {

        return villageRepository.countItemByVillage().stream().map(
                row -> {
                    String villageName = (String) row[0];
                    Long itemCount = (Long) row[1];
                    return new RegionalAnalysisResponseDto(villageName, itemCount);
                }
        ).limit(3).toList();

    }
}
