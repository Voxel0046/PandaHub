package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.cosmetics.CosmeticMenu;
import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CosmeticHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public CosmeticHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onCosmetic(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() == null) return;

            Hotbar cosmetic = hotbarManager.getHotbar("COSMETICS");

            if (cosmetic.isEnable() && cosmetic.isHotbarItem(event.getItem())) {
                event.setCancelled(true);
                CosmeticMenu cosmeticMenu = new CosmeticMenu();
                cosmeticMenu.openMenu(event.getPlayer());
            }
        }
    }
}
