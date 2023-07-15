package com.depromeet.util;

import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.WEEKS;


public class WeekUtil {
    private static final String THIS_WEEK = "이번 주";
    private static final String LAST_WEEK = "지난 주";
    private static final String WEEKS_AGO = "%d주 전";

    public static String getWeeksAgo(LocalDateTime date) {
        long weeksDiff = WEEKS.between(date, LocalDateTime.now());

        return weeksDiff == 0 ? THIS_WEEK :
                weeksDiff == 1 ? LAST_WEEK :
                        String.format(WEEKS_AGO, weeksDiff);
    }
}
