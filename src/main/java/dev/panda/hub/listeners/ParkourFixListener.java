package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import us.ajg0702.parkour.api.events.PlayerEndParkourEvent;

public class ParkourFixListener implements Listener {

    public ParkourFixListener(PandaHub plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onParkourFinish(PlayerEndParkourEvent event) {
        Player player = event.getPlayer();

        PandaHub.get().getModuleService().getManagerModule().getHotbarManager().setHotbar(player);
        PandaHub.get().getModuleService().getManagerModule().getCustomHotbarManager().setCustomHotbar(player);
    }
}
