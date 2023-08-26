package com.streetdrop.domains.user.scheduler;

import com.streetdrop.domains.user.jobs.UserLevelJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLevelBatchScheduler {
	private final UserLevelJob userLevelJob;

	@Scheduled(cron = "0 0/30 * * * *")
	public void runUserLevelJob() {
		userLevelJob.run();
	}
}
