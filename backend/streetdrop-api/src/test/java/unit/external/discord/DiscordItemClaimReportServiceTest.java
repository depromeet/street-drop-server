package unit.external.discord;

import com.depromeet.external.discord.DiscordService;
import com.depromeet.item.vo.ItemClaimStatus;
import com.depromeet.report.dto.ItemClaimReportDto;
import com.depromeet.report.service.DiscordItemClaimReportService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DiscordItemClaimReportServiceTest {

	@DisplayName("디스코드 메시지 테스트")
	@Test
	public void messageTest() throws Exception {

		ItemClaimReportDto itemClaimReportDto = ItemClaimReportDto.builder()
				.itemClaimId(1L)
				.itemClaimReason("기타")
				.itemClaimStatus(ItemClaimStatus.WAITING)
				.reportUserId(428L)
				.itemId(878L)
				.itemContent("TEST")
				.build();

		DiscordItemClaimReportService discordItemClaimReportService = new DiscordItemClaimReportService();
		discordItemClaimReportService.sendReport(itemClaimReportDto);
	}
}
