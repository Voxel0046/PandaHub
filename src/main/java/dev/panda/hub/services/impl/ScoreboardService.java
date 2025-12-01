package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;

import java.util.List;

public class ScoreboardService extends Service {

    private final FileConfig scoreboardConfig = PandaHub.get().getModuleService().getFileModule().getFile("scoreboard");

    public static boolean ENABLE;

    public static boolean TITLE_ANIMATED_ENABLE;
    public static double TITLE_ANIMATED_INTERVAL;
    public static List<String> TITLE_ANIMATED_TITLE;

    public static boolean FOOTER_ANIMATED_ENABLE;
    public static double FOOTER_ANIMATED_INTERVAL;
    public static List<String> FOOTER_ANIMATED_FOOTER;

    public static List<String> PVP;
    public static List<String> MAIN;
    public static List<String> QUEUE;
    public static List<String> CUSTOM_TIMER;

    @Override
    public void initialize() {
        ENABLE = scoreboardConfig.getBoolean("SCOREBOARD.ENABLE");

        TITLE_ANIMATED_ENABLE = scoreboardConfig.getBoolean("SCOREBOARD.TITLE_ANIMATED.ENABLE");
        TITLE_ANIMATED_INTERVAL = scoreboardConfig.getDouble("SCOREBOARD.TITLE_ANIMATED.INTERVAL");
        TITLE_ANIMATED_TITLE = scoreboardConfig.getStringList("SCOREBOARD.TITLE_ANIMATED.TITLE");

        FOOTER_ANIMATED_ENABLE = scoreboardConfig.getBoolean("SCOREBOARD.FOOTER_ANIMATED.ENABLE");
        FOOTER_ANIMATED_INTERVAL = scoreboardConfig.getDouble("SCOREBOARD.FOOTER_ANIMATED.INTERVAL");
        FOOTER_ANIMATED_FOOTER = scoreboardConfig.getStringList("SCOREBOARD.FOOTER_ANIMATED.FOOTER");

        PVP = scoreboardConfig.getStringList("SCOREBOARD.PVP");
        MAIN = scoreboardConfig.getStringList("SCOREBOARD.MAIN");
        QUEUE = scoreboardConfig.getStringList("SCOREBOARD.QUEUE");
        CUSTOM_TIMER = scoreboardConfig.getStringList("SCOREBOARD.CUSTOM_TIMER");
    }
}
