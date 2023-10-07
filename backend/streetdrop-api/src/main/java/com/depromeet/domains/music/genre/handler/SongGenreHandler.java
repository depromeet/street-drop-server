package com.depromeet.domains.music.genre.handler;

import com.depromeet.domains.music.event.CreateSongGenreEvent;
import com.depromeet.domains.music.genre.service.SongGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class SongGenreHandler {

    private final SongGenreService songGenreService;

    @EventListener
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handleEvent(CreateSongGenreEvent event) {
        songGenreService.createSongGenres(event.song, event.genreNameList);
    }

}
