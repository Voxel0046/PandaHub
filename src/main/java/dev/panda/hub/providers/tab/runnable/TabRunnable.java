package dev.panda.hub.providers.tab.runnable;

import dev.panda.hub.providers.tab.TablistException;
import dev.panda.hub.providers.tab.adapter.TabAdapter;
import dev.panda.hub.providers.tab.entry.TabEntry;
import dev.panda.hub.providers.tab.layout.TabLayout;
import dev.panda.hub.providers.tab.skin.Skin;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

@AllArgsConstructor
public class TabRunnable implements Runnable {

    private TabAdapter adapter;

    @Override
    public void run() {
        if (adapter == null) {
            //System.err.println("TabAdapter is null in TabRunnable. Aborting tab update.");
            return;
        }

        try {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player != null && player.isOnline()) {
                    TabLayout layout = TabLayout.getLayoutMapping().get(player.getUniqueId());

                    if (layout == null) {
                        continue;
                    }

                    try {
                        List<TabEntry> lines = adapter.getLines(player);
                        if (lines == null) {
                            //System.err.println("adapter.getLines(player) returned null for player: " + player.getName());
                            continue;
                        }

                        for (TabEntry entry : lines) {
                            if (entry == null) {
                                continue;
                            }

                            int column = entry.getColumn();
                            int row = entry.getRow();
                            String text = entry.getText() != null ? entry.getText() : "";
                            int ping = entry.getPing();
                            Skin skin = entry.getSkin() != null ? entry.getSkin() : Skin.DEFAULT_SKIN;

                            layout.setHeaderAndFooter();
                            layout.update(column, row, text, ping, skin);
                        }

                        for (int row = 0; row < 20; row++) {
                            for (int column = 0; column < 4; column++) {
                                if (layout.getByLocation(lines, column, row) == null) {
                                    layout.update(column, row, "", 0, Skin.DEFAULT_SKIN);
                                }
                            }
                        }
                    } catch (Exception ex) {
                        if (layout.getExceptions().incrementAndGet() > 3) {
                            TabLayout.getLayoutMapping().remove(player.getUniqueId());
                        }

                        ex.printStackTrace();
                        throw new TablistException("There was an error updating " + player.getName() + "'s tablist.");
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
