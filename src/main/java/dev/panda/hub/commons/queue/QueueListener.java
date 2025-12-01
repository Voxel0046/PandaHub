package dev.panda.hub.commons.queue;

import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class QueueListener implements Listener {

    public QueueListener(PandaHub plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Queue queue = QueueHandler.getQueue(player);
        if (queue != null) queue.removeEntry(player);
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        Queue queue = QueueHandler.getQueue(player);
        if (queue != null) queue.removeEntry(player);
    }
}
