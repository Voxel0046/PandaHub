package dev.panda.hub.providers.scoreboard;

import dev.panda.hub.PandaHub;
import dev.panda.hub.providers.ScoreboardProvider;
import dev.panda.hub.services.impl.ScoreboardService;
import lombok.Getter;

public class ScoreboardHook {

    @Getter
    private static Scoreboard scoreboard;

    public static void init(PandaHub plugin) {
        if (ScoreboardService.ENABLE) {
            scoreboard = new Scoreboard(plugin, new ScoreboardProvider());
            scoreboard.setTicks(2);
        }
    }
}
