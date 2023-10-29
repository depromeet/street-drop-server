package unit.external.discord;


import com.depromeet.external.discord.DiscordService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class DiscordServiceTest {
	public static final String WEB_HOOK_URL = "TEST";
	public static final String CHANNEL = "claim";

	@DisplayName("디스코드 메시지 테스트")
	@Test
	public void messageTest() throws Exception {
		DiscordService discordService = new DiscordService();
		discordService.sendMessages(WEB_HOOK_URL,"테스트 입니다", CHANNEL);
	}
}