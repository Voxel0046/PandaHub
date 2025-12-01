package dev.panda.hub.providers.tab.adapter;


import dev.panda.hub.providers.tab.entry.TabEntry;
import org.bukkit.entity.Player;

import java.util.List;

public interface TabAdapter {
    String getHeader(Player player);

    String getFooter(Player player);

    List<TabEntry> getLines(Player player);
}
