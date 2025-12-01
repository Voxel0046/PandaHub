package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BorderListener implements Listener {

    public BorderListener(PandaHub plugin, boolean enabled) {
        if (!enabled) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!isNearBorder(event.getTo()) && isNearBorder(event.getFrom())) {

            if (event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockZ() == event.getTo().getBlockZ()) return;

            Player player = event.getPlayer();
            PandaHub.get().getModuleService().getManagerModule().getSpawnManager().toSpawn(player, true);
            ChatUtil.sendMessage(player, LangService.WORLD_BORDER);
        }
    }

    private boolean isNearBorder(Location location) {
        return Math.abs(location.getBlockX()) <= ConfigService.WORLD_BORDER && Math.abs(location.getBlockZ()) <= ConfigService.WORLD_BORDER;
    }
}
