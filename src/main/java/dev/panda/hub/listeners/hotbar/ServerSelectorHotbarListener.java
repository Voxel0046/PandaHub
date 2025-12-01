package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.commons.selector.server.menu.ServerSelectorMenu;
import dev.panda.hub.utilities.compatibility.sound.CompatibleSound;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ServerSelectorHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public ServerSelectorHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onServer(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar serverSelector = hotbarManager.getHotbar("SERVER_SELECTOR");

            if (serverSelector.isEnable() && serverSelector.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                CompatibleSound.CLICK.play(event.getPlayer());
                new ServerSelectorMenu().openMenu(event.getPlayer());
            }
        }
    }
}
