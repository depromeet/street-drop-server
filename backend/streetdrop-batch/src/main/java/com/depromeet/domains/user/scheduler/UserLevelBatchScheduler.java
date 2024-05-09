package com.depromeet.domains.user.scheduler;

import com.depromeet.domains.user.jobs.UserGuideJob;
import com.depromeet.domains.user.jobs.UserLevelJob;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLevelBatchScheduler {
	private final UserLevelJob userLevelJob;
	private final UserGuideJob userGuideJob;

	@Scheduled(cron = "0 0/30 * * * *")
	public void runUserLevelJob() {
		userLevelJob.run();
	}

	@Scheduled(cron = "0 0/30 * * * *")
	public void runUserGuideJob() {
		userGuideJob.run();
	}
}
