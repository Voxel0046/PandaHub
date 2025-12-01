package dev.panda.hub.providers.scoreboard;

import dev.panda.hub.services.impl.ScoreboardService;
import dev.panda.hub.utilities.TaskUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreboardAnimated {

    public static String TITLE;
    public static String FOOTER;

    public static void init() {
        List<String> titles = ScoreboardService.TITLE_ANIMATED_TITLE;
        List<String> footers = ScoreboardService.FOOTER_ANIMATED_FOOTER;

        TITLE = titles.get(0);
        FOOTER = footers.get(0);

        if (ScoreboardService.TITLE_ANIMATED_ENABLE) {
            AtomicInteger atomicInteger = new AtomicInteger();

            TaskUtil.runTimerAsync(() -> {
                if (atomicInteger.get() == titles.size()) atomicInteger.set(0);

                TITLE = titles.get(atomicInteger.getAndIncrement());

            }, 0L, (long) (ScoreboardService.TITLE_ANIMATED_INTERVAL * 20L));
        }

        if (ScoreboardService.FOOTER_ANIMATED_ENABLE) {
            AtomicInteger atomicInteger = new AtomicInteger();

            TaskUtil.runTimerAsync(() -> {
                if (atomicInteger.get() == footers.size()) atomicInteger.set(0);

                FOOTER = footers.get(atomicInteger.getAndIncrement());

            }, 0L, (long) (ScoreboardService.FOOTER_ANIMATED_INTERVAL * 20L));
        }
    }
}
