package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ConfigService extends Service {

    private final FileConfig configFile = PandaHub.get().getModuleService().getFileModule().getFile("config");

    public static String DISCORD;
    public static String STORE;
    public static String TEAMSPEAK;
    public static String TWITTER;
    public static String WEBSITE;

    public static String TIME_ZONE;
    public static String TIME_DATE;
    public static String TIME_HOUR;

    public static int WORLD_BORDER;

    public static boolean HIDE_AND_SHOW_COOLDOWN_ENABLE;
    public static int HIDE_AND_SHOW_COOLDOWN_TIME;
    public static String HIDE_AND_SHOW_COOLDOWN_MESSAGE;

    public static boolean MENU_FILL_BUTTON_ENABLE;
    public static ItemStack MENU_FILL_BUTTON_ICON;

    public static boolean MENU_BACK_BUTTON_ENABLE;
    public static ItemStack MENU_BACK_BUTTON_ICON;

    public static boolean ANNOUNCEMENT_ENABLE;
    public static int ANNOUNCEMENT_INTERVAL;

    public static boolean DOUBLE_JUMP_ENABLE;
    public static String DOUBLE_JUMP_EFFECT;
    public static String DOUBLE_JUMP_SOUND;
    public static double DOUBLE_JUMP_VELOCITY;

    public static boolean ENDER_BUTT_RIDE;
    public static String ENDER_BUTT_SOUND;
    public static double ENDER_BUTT_VELOCITY;

    public static List<String> DISABLE_COMMAND_COMMANDS;

    public static boolean BLOCK_COMMAND_ENABLE;
    public static String BLOCK_COMMAND_MESSAGE;
    public static List<String> BLOCK_COMMAND_COMMANDS;

    public static List<String> DENY_BLOCKS_INTERACT;
    public static List<String> ALLOW_BLOCKS_INTERACT;

    public static boolean CUSTOM_CHAT_ENABLE;
    public static String CUSTOM_CHAT_FORMAT;

    public static boolean CUSTOM_MESSAGE_ENABLE;
    public static String CUSTOM_MESSAGE_FORMAT_TO;
    public static String CUSTOM_MESSAGE_FORMAT_FROM;

    public static boolean CUSTOM_JOIN_ENABLE;
    public static String CUSTOM_JOIN_PERMISSION;
    public static String CUSTOM_JOIN_MESSAGE_JOIN;
    public static String CUSTOM_JOIN_MESSAGE_QUIT;

    public static boolean PLAYER_JOIN_SOUND_ENABLE;
    public static String PLAYER_JOIN_SOUND_TYPE;
    public static boolean PLAYER_JOIN_SPEED_ENABLE;
    public static double PLAYER_JOIN_SPEED_VALUE;
    public static boolean PLAYER_JOIN_MESSAGE_ENABLE;
    public static boolean PLAYER_JOIN_MESSAGE_CLEAR;
    public static List<String> PLAYER_JOIN_MESSAGE_MESSAGE;
    public static boolean PLAYER_JOIN_TITLE_ENABLE;
    public static String PLAYER_JOIN_TITLE_TITLE;
    public static String PLAYER_JOIN_TITLE_SUBTITLE;
    public static boolean PLAYER_JOIN_LEVEL_ENABLE;
    public static int PLAYER_JOIN_LEVEL;

    public static boolean DISABLE_BLOCK_BREAK;
    public static boolean DISABLE_BLOCK_PLACE;
    public static boolean DISABLE_DROP_ITEM;
    public static boolean DISABLE_PICKUP_ITEM;
    public static boolean DISABLE_PVP;
    public static boolean DISABLE_CHAT;
    public static boolean DISABLE_PLAYER_SEE_IN_HUB;
    public static boolean DISABLE_WORLD_BORDER;
    public static boolean DISABLE_CLEAR_HOTBAR;

    public static boolean ENABLE_BLOCK_BREAK_MESSAGE;
    public static boolean ENABLE_BLOCK_PLACE_MESSAGE;
    public static boolean ENABLE_DROP_ITEM_MESSAGE;
    public static boolean ENABLE_PICKUP_ITEM_MESSAGE;
    public static boolean ENABLE_PVP_MESSAGE;

    public static List<String> LUNAR_LINES;

    @Override
    public void initialize() {
        DISCORD = configFile.getString("DISCORD");
        STORE = configFile.getString("STORE");
        TEAMSPEAK = configFile.getString("TEAMSPEAK");
        TWITTER = configFile.getString("TWITTER");
        WEBSITE = configFile.getString("WEBSITE");

        TIME_ZONE = configFile.getString("TIME.ZONE");
        TIME_DATE = configFile.getString("TIME.DATE");
        TIME_HOUR = configFile.getString("TIME.HOUR");

        WORLD_BORDER = configFile.getInt("WORLD_BORDER");

        HIDE_AND_SHOW_COOLDOWN_ENABLE = configFile.getBoolean("HIDE_AND_SHOW.COOLDOWN.ENABLE");
        HIDE_AND_SHOW_COOLDOWN_TIME = configFile.getInt("HIDE_AND_SHOW.COOLDOWN.TIME");
        HIDE_AND_SHOW_COOLDOWN_MESSAGE = configFile.getString("HIDE_AND_SHOW.COOLDOWN.MESSAGE");

        MENU_FILL_BUTTON_ENABLE = configFile.getBoolean("MENU.FILL_BUTTON.ENABLE");
        MENU_FILL_BUTTON_ICON = new ItemBuilder(configFile.getString("MENU.FILL_BUTTON.ICON.MATERIAL"))
                .data(configFile.getInt("MENU.FILL_BUTTON.ICON.DATA"))
                .name(configFile.getString("MENU.FILL_BUTTON.ICON.DISPLAYNAME"))
                .lore(configFile.getStringList("MENU.FILL_BUTTON.ICON.DESCRIPTION"))
                .build();

        MENU_BACK_BUTTON_ENABLE = configFile.getBoolean("MENU.BACK_BUTTON.ENABLE");
        MENU_BACK_BUTTON_ICON = new ItemBuilder(configFile.getString("MENU.BACK_BUTTON.ICON.MATERIAL"))
                .data(configFile.getInt("MENU.BACK_BUTTON.ICON.DATA"))
                .name(configFile.getString("MENU.BACK_BUTTON.ICON.DISPLAYNAME"))
                .lore(configFile.getStringList("MENU.BACK_BUTTON.ICON.DESCRIPTION"))
                .build();

        ANNOUNCEMENT_ENABLE = configFile.getBoolean("ANNOUNCEMENT.ENABLE");
        ANNOUNCEMENT_INTERVAL = configFile.getInt("ANNOUNCEMENT.INTERVAL");

        DOUBLE_JUMP_ENABLE = configFile.getBoolean("DOUBLE_JUMP.ENABLE");
        DOUBLE_JUMP_EFFECT = configFile.getString("DOUBLE_JUMP.EFFECT").toUpperCase();
        DOUBLE_JUMP_SOUND = configFile.getString("DOUBLE_JUMP.SOUND").toUpperCase();
        DOUBLE_JUMP_VELOCITY = configFile.getDouble("DOUBLE_JUMP.VELOCITY");

        ENDER_BUTT_RIDE = configFile.getBoolean("ENDER_BUTT.RIDE");
        ENDER_BUTT_SOUND = configFile.getString("ENDER_BUTT.SOUND").toUpperCase();
        ENDER_BUTT_VELOCITY = configFile.getDouble("ENDER_BUTT.VELOCITY");

        DISABLE_COMMAND_COMMANDS = configFile.getStringList("DISABLE_COMMAND.COMMANDS");

        BLOCK_COMMAND_ENABLE = configFile.getBoolean("BLOCK_COMMAND.ENABLE");
        BLOCK_COMMAND_MESSAGE = configFile.getString("BLOCK_COMMAND.MESSAGE");
        BLOCK_COMMAND_COMMANDS = configFile.getStringList("BLOCK_COMMAND.COMMANDS");

        DENY_BLOCKS_INTERACT = configFile.getStringList("DENY_BLOCKS_INTERACT");
        ALLOW_BLOCKS_INTERACT = configFile.getStringList("ALLOW_BLOCKS_INTERACT");

        CUSTOM_CHAT_ENABLE = configFile.getBoolean("CUSTOM_CHAT.ENABLE");
        CUSTOM_CHAT_FORMAT = configFile.getString("CUSTOM_CHAT.FORMAT");

        CUSTOM_MESSAGE_ENABLE = configFile.getBoolean("CUSTOM_MESSAGE.ENABLE");
        CUSTOM_MESSAGE_FORMAT_TO = configFile.getString("CUSTOM_MESSAGE.FORMAT.TO");
        CUSTOM_MESSAGE_FORMAT_FROM = configFile.getString("CUSTOM_MESSAGE.FORMAT.FROM");

        CUSTOM_JOIN_ENABLE = configFile.getBoolean("CUSTOM_JOIN.ENABLE");
        CUSTOM_JOIN_PERMISSION = configFile.getString("CUSTOM_JOIN.PERMISSION");
        CUSTOM_JOIN_MESSAGE_JOIN = configFile.getString("CUSTOM_JOIN.MESSAGE.JOIN");
        CUSTOM_JOIN_MESSAGE_QUIT = configFile.getString("CUSTOM_JOIN.MESSAGE.QUIT");

        PLAYER_JOIN_SOUND_ENABLE = configFile.getBoolean("PLAYER_JOIN.SOUND.ENABLE");
        PLAYER_JOIN_SOUND_TYPE = configFile.getString("PLAYER_JOIN.SOUND.TYPE").toUpperCase();
        PLAYER_JOIN_SPEED_ENABLE = configFile.getBoolean("PLAYER_JOIN.SPEED.ENABLE");
        PLAYER_JOIN_SPEED_VALUE = configFile.getDouble("PLAYER_JOIN.SPEED.VALUE");
        PLAYER_JOIN_MESSAGE_ENABLE = configFile.getBoolean("PLAYER_JOIN.MESSAGE.ENABLE");
        PLAYER_JOIN_MESSAGE_CLEAR = configFile.getBoolean("PLAYER_JOIN.MESSAGE.CLEAR");
        PLAYER_JOIN_MESSAGE_MESSAGE = configFile.getStringList("PLAYER_JOIN.MESSAGE.MESSAGE");
        PLAYER_JOIN_TITLE_ENABLE = configFile.getBoolean("PLAYER_JOIN.TITLE.ENABLE");
        PLAYER_JOIN_TITLE_TITLE = configFile.getString("PLAYER_JOIN.TITLE.TITLE");
        PLAYER_JOIN_TITLE_SUBTITLE = configFile.getString("PLAYER_JOIN.TITLE.SUBTITLE");
        PLAYER_JOIN_LEVEL_ENABLE = configFile.getBoolean("PLAYER_JOIN.XP_LEVEL.ENABLE");
        PLAYER_JOIN_LEVEL = configFile.getInt("PLAYER_JOIN.XP_LEVEL.LEVEL");

        DISABLE_BLOCK_BREAK = configFile.getBoolean("DISABLE_BLOCK_BREAK");
        DISABLE_BLOCK_PLACE = configFile.getBoolean("DISABLE_BLOCK_PLACE");
        DISABLE_DROP_ITEM = configFile.getBoolean("DISABLE_DROP_ITEM");
        DISABLE_PICKUP_ITEM = configFile.getBoolean("DISABLE_PICKUP_ITEM");
        DISABLE_PVP = configFile.getBoolean("DISABLE_PVP");
        DISABLE_CHAT = configFile.getBoolean("DISABLE_CHAT");
        DISABLE_PLAYER_SEE_IN_HUB = configFile.getBoolean("DISABLE_PLAYER_SEE_IN_HUB");
        DISABLE_WORLD_BORDER = configFile.getBoolean("DISABLE_WORLD_BORDER");
        DISABLE_CLEAR_HOTBAR = configFile.getBoolean("DISABLE_CLEAR_HOTBAR");

        ENABLE_BLOCK_BREAK_MESSAGE = configFile.getBoolean("ENABLE_BLOCK_BREAK_MESSAGE");
        ENABLE_BLOCK_PLACE_MESSAGE = configFile.getBoolean("ENABLE_BLOCK_PLACE_MESSAGE");
        ENABLE_DROP_ITEM_MESSAGE = configFile.getBoolean("ENABLE_DROP_ITEM_MESSAGE");
        ENABLE_PICKUP_ITEM_MESSAGE = configFile.getBoolean("ENABLE_PICKUP_ITEM_MESSAGE");
        ENABLE_PVP_MESSAGE = configFile.getBoolean("ENABLE_PVP_MESSAGE");

        LUNAR_LINES = configFile.getStringList("LUNAR_TAG.LINES");
    }
}
