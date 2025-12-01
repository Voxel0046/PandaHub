package dev.panda.hub.providers;

import dev.panda.hub.PandaHub;
import dev.panda.hub.hooks.PlaceholderAPIHook;
import dev.panda.hub.providers.tab.adapter.TabAdapter;
import dev.panda.hub.providers.tab.entry.TabEntry;
import dev.panda.hub.providers.tab.skin.Skin;
import dev.panda.hub.services.impl.TablistService;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.ajg0702.queue.api.AjQueueAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TablistProvider implements TabAdapter {

    private static final Map<String, Boolean> SERVER_STATUS = new HashMap<>();

    @Override
    public String getHeader(Player player) {
        return PlaceholderAPIHook.isPlaceholderAPI() ? PlaceholderAPI.setPlaceholders(player, TablistService.HEADER) : TablistService.HEADER;
    }

    @Override
    public String getFooter(Player player) {
        return PlaceholderAPIHook.isPlaceholderAPI() ? PlaceholderAPI.setPlaceholders(player, TablistService.FOOTER) : TablistService.FOOTER;
    }

    @Override
    public List<TabEntry> getLines(Player player) {
        List<TabEntry> lines = new ArrayList<>();

        for (String column : TablistService.LINES.keySet()) {
            int x = getColumnIndex(column);

            for (Map.Entry<Integer, TablistService.LineData> entry : TablistService.LINES.get(column).entrySet()) {
                int y = entry.getKey();
                TablistService.LineData data = entry.getValue();

                String text = data.getText();

                if (y < 0 || y > 19) continue;

                if (PlaceholderAPIHook.isPlaceholderAPI()) {
                    text = PlaceholderAPI.setPlaceholders(player, text);
                }

                Skin skin = resolveSkin(data.getSkin().toUpperCase());

                lines.add(new TabEntry(x, y, text, skin));
            }
        }

        return lines;
    }


    private int getColumnIndex(String column) {
        switch (column.toUpperCase()) {
            case "LEFT":
                return 0;
            case "MIDDLE":
                return 1;
            case "RIGHT":
                return 2;
            case "FAR_RIGHT":
                return 3;
            default:
                return -1;
        }
    }

    private Skin resolveSkin(String raw) {
        if (raw == null) return Skin.DEFAULT_SKIN;

        if (!raw.toUpperCase().startsWith("STATUS_")) {
            return TablistService.HEADS.getOrDefault(raw.toUpperCase(), Skin.DEFAULT_SKIN);
        }

        String server = raw.substring(7);

        if (isServerOnline(server)) {
            return TablistService.HEADS.getOrDefault("ONLINE", Skin.DEFAULT_SKIN);
        } else {
            return TablistService.HEADS.getOrDefault("OFFLINE", Skin.DEFAULT_SKIN);
        }
    }

    private boolean isServerOnline(String server) {
        Future<String> future = AjQueueAPI.getSpigotInstance().getServerStatusString(server);

        Bukkit.getScheduler().runTaskAsynchronously(PandaHub.get(), () -> {
            try {
                String result = future.get(2, TimeUnit.SECONDS); // ajqueue returns "online" or "offline"
                String status = ChatColor.stripColor(result.toLowerCase());
                SERVER_STATUS.put(server, status.contains("online"));
            } catch (Exception ignored) {
                SERVER_STATUS.put(server, false);
            }
        });

        return SERVER_STATUS.getOrDefault(server, false);
    }
}
