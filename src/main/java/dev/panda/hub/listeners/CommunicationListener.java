package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.cache.CommunicationCache;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class CommunicationListener implements Listener {

    public CommunicationListener(PandaHub plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        CommunicationCache.remove(event.getPlayer().getUniqueId());
    }
}
