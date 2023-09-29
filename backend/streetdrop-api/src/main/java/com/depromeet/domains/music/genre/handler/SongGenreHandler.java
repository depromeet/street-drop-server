package com.depromeet.domains.music.genre.handler;

import com.depromeet.domains.music.event.CreateSongGenreEvent;
import com.depromeet.domains.music.genre.service.SongGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongGenreHandler {

    private final SongGenreService songGenreService;

    @EventListener
    @Async
    public void handleEvent(CreateSongGenreEvent event) {
        songGenreService.createSongGenres(event.song, event.genreNameList);
    }

}
