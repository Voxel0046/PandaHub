package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.commons.selector.lobby.menu.LobbySelectorMenu;
import dev.panda.hub.utilities.compatibility.sound.CompatibleSound;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LobbySelectorHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public LobbySelectorHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onLobby(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar lobbySelector = hotbarManager.getHotbar("LOBBY_SELECTOR");

            if (lobbySelector.isEnable() && lobbySelector.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                CompatibleSound.CLICK.play(event.getPlayer());
                new LobbySelectorMenu().openMenu(event.getPlayer());
            }
        }
    }
}
