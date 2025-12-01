package dev.panda.hub.module.impl;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.module.Module;
import dev.panda.hub.utilities.file.FileConfig;
import lombok.Getter;

import java.util.Map;

public class FileModule extends Module {

    @Getter
    private final Map<String, FileConfig> files = Maps.newHashMap();

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public void onEnable(PandaHub plugin) {
        files.put("config", new FileConfig(plugin, "config.yml"));
        files.put("lang", new FileConfig(plugin, "lang/lang.yml"));
        files.put("cosmetic", new FileConfig(plugin, "menus/cosmetic.yml"));
        files.put("outfit", new FileConfig(plugin, "menus/outfit.yml"));
        files.put("trail", new FileConfig(plugin, "menus/trail.yml"));
        files.put("gadget", new FileConfig(plugin, "menus/gadget.yml"));
        files.put("message-color", new FileConfig(plugin, "menus/message-color.yml"));
        files.put("chat-color", new FileConfig(plugin, "menus/chat-color.yml"));
        files.put("scoreboard", new FileConfig(plugin, "provider/scoreboard.yml"));
        files.put("tablist", new FileConfig(plugin, "provider/tablist.yml"));
        files.put("server", new FileConfig(plugin, "selector/server.yml"));
        files.put("sub-server", new FileConfig(plugin, "selector/sub_server.yml"));
        files.put("lobby", new FileConfig(plugin, "selector/lobby.yml"));
        files.put("hotbar", new FileConfig(plugin, "hotbar/hotbar.yml"));
        files.put("custom-hotbar", new FileConfig(plugin, "hotbar/custom-hotbar.yml"));
        files.put("queue", new FileConfig(plugin, "queue.yml"));
        files.put("outfits", new FileConfig(plugin, "outfits.yml"));
        files.put("spawn-data", new FileConfig(plugin, "data/spawn-data.yml"));
        files.put("player-data", new FileConfig(plugin, "data/player-data.yml"));
        files.put("pvpmode-data", new FileConfig(plugin, "data/pvpmode-data.yml"));
    }

    public FileConfig getFile(String name) {
        return files.get(name);
    }

    public void reload() {
        for (FileConfig file : files.values()) {
            file.reload();
        }
    }
}
