package dev.panda.hub.providers.tab;

import dev.panda.hub.providers.tab.adapter.TabAdapter;
import dev.panda.hub.providers.tab.listener.TabListener;
import dev.panda.hub.providers.tab.packet.TabPacket;
import dev.panda.hub.providers.tab.runnable.TabRunnable;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Tablist {

    @Getter
    private static Tablist instance;

    private final TabAdapter adapter;

    public Tablist(TabAdapter adapter, JavaPlugin plugin, long time) {
        instance = this;
        this.adapter = adapter;

        new TabPacket(plugin);

        Bukkit.getServer().getPluginManager().registerEvents(new TabListener(this), plugin);
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new TabRunnable(adapter), 60L, time); //TODO: async to run 1 millis
    }
}
