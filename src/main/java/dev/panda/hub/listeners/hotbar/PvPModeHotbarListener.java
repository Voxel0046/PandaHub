package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.commons.pvp.events.PvPModeJoinEvent;
import dev.panda.hub.commons.pvp.events.PvPModeLeaveEvent;
import dev.panda.hub.utilities.TaskUtil;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PvPModeHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public PvPModeHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPvPJoinItem(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar pvpJoin = hotbarManager.getHotbar("PVP_MODE_JOIN");

            if (pvpJoin.isEnable() && pvpJoin.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                Bukkit.getPluginManager().callEvent(new PvPModeJoinEvent(event.getPlayer()));
            }
        }
    }

    @EventHandler
    private void onPvPLeaveItem(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar pvpLeave = hotbarManager.getHotbar("PVP_MODE_LEAVE");

            if (pvpLeave.isEnable() && pvpLeave.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                TaskUtil.runLater(() -> Bukkit.getPluginManager().callEvent(new PvPModeLeaveEvent(event.getPlayer())), 1L);
            }
        }
    }
}
