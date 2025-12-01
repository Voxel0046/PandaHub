package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.providers.tab.skin.Skin;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class TablistService extends Service {

    private final FileConfig tablistConfig = PandaHub.get().getModuleService().getFileModule().getFile("tablist");

    public static boolean ENABLE;
    public static String HEADER;
    public static String FOOTER;
    public static Map<String, Map<Integer, LineData>> LINES = new HashMap<>();
    public static Map<String, Skin> HEADS = new HashMap<>();

    @Override
    public void initialize() {
        ENABLE = tablistConfig.getBoolean("TABLIST.ENABLE");
        HEADER = tablistConfig.getString("TABLIST.HEADER");
        FOOTER = tablistConfig.getString("TABLIST.FOOTER");

        LINES.clear();
        HEADS.clear();

        for (String skinKey : tablistConfig.getConfiguration().getConfigurationSection("SKINS").getKeys(false)) {
            String path = "SKINS." + skinKey;

            String value = tablistConfig.getString(path + ".VALUE");
            String signature = tablistConfig.getString(path + ".SIGNATURE");

            HEADS.put(skinKey.toUpperCase(), new Skin(value, signature));
        }

        for (String section : tablistConfig.getConfiguration().getConfigurationSection("TABLIST.LINES").getKeys(false)) {
            Map<Integer, LineData> sectionLines = new HashMap<>();

            for (String key : tablistConfig.getConfiguration().getConfigurationSection("TABLIST.LINES." + section).getKeys(false)) {
                int slot = Integer.parseInt(key);

                String text = tablistConfig.getString("TABLIST.LINES." + section + "." + key + ".TEXT");
                String skin = tablistConfig.getString("TABLIST.LINES." + section + "." + key + ".SKIN");

                sectionLines.put(slot, new LineData(text, skin));
            }

            LINES.put(section.toUpperCase(), sectionLines);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class LineData {

        private final String text;
        private final String skin;

    }
}

