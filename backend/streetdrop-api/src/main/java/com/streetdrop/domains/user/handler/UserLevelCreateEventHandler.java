package com.streetdrop.domains.user.handler;

import com.streetdrop.domains.user.event.UserLevelSetEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class UserLevelCreateEventHandler {

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void createLevel(UserLevelSetEvent event) {
		// TODO: 이벤트 처리 로직 필요 시 추가.
	}

}
