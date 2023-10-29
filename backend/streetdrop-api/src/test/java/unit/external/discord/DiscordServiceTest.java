package unit.external.discord;


import com.depromeet.external.discord.DiscordService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class DiscordServiceTest {
	public static final String WEB_HOOK_URL = "https://discord.com/api/webhooks/1165606257181327360/04XWl4J0Xq2e-2Cg7tj_rXHxnqd8DuB0pwwKc2Jqi7jmct7k6EE4Tw6IJWcoBxcrCFul";
	public static final String CHANNEL = "claim";

	@DisplayName("디스코드 메시지 테스트")
	@Test
	public void messageTest() throws Exception {
		DiscordService discordService = new DiscordService();
		discordService.sendMessages("테스트 입니다", CHANNEL);
	}
}