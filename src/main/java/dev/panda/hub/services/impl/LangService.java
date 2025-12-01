package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;

import java.util.List;

public class LangService extends Service {

    private final FileConfig langConfig = PandaHub.get().getModuleService().getFileModule().getFile("lang");

    public static List<String> DISCORD;
    public static List<String> STORE;
    public static List<String> TEAMSPEAK;
    public static List<String> TWITTER;
    public static List<String> WEBSITE;

    public static String BLOCK_BREAK;
    public static String BLOCK_PLACE;
    public static String DROP_ITEM;
    public static String PICKUP_ITEM;
    public static String DAMAGE;
    public static String DISABLE_CHAT;
    public static String WORLD_BORDER;

    public static String SPAWN_SET;
    public static String SPAWN_NOT_SET;
    public static String SPAWN_SPAWNED;

    public static String PVP_LOCATION_SET;
    public static String PVP_LOCATION_NOT_SET;
    public static String PVP_LOCATION_SPAWNED;
    public static String PVP_LOOT;

    public static String BUILD_ENABLE;
    public static String BUILD_DISABLE;

    public static String HIDE_PLAYER;
    public static String SHOW_PLAYER;

    public static String SKULL;

    @Override
    public void initialize() {
        DISCORD = langConfig.getStringList("DISCORD");
        STORE = langConfig.getStringList("STORE");
        TEAMSPEAK = langConfig.getStringList("TEAMSPEAK");
        TWITTER = langConfig.getStringList("TWITTER");
        WEBSITE = langConfig.getStringList("WEBSITE");

        BLOCK_BREAK = langConfig.getString("BLOCK_BREAK");
        BLOCK_PLACE = langConfig.getString("BLOCK_PLACE");
        DROP_ITEM = langConfig.getString("DROP_ITEM");
        PICKUP_ITEM = langConfig.getString("PICKUP_ITEM");
        DAMAGE = langConfig.getString("DAMAGE");
        DISABLE_CHAT = langConfig.getString("DISABLE_CHAT");
        WORLD_BORDER = langConfig.getString("WORLD_BORDER");

        SPAWN_SET = langConfig.getString("SPAWN.SET");
        SPAWN_NOT_SET = langConfig.getString("SPAWN.NOT_SET");
        SPAWN_SPAWNED = langConfig.getString("SPAWN.SPAWNED");

        PVP_LOCATION_SET = langConfig.getString("PVP.LOCATION.SET");
        PVP_LOCATION_NOT_SET = langConfig.getString("PVP.LOCATION.NOT_SET");
        PVP_LOCATION_SPAWNED = langConfig.getString("PVP.LOCATION.SPAWNED");
        PVP_LOOT = langConfig.getString("PVP.LOOT");

        BUILD_ENABLE = langConfig.getString("BUILD.ENABLE");
        BUILD_DISABLE = langConfig.getString("BUILD.DISABLE");

        HIDE_PLAYER = langConfig.getString("HIDE_PLAYER");
        SHOW_PLAYER = langConfig.getString("SHOW_PLAYER");

        SKULL = langConfig.getString("SKULL");
    }
}
