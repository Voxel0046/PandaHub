package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.services.impl.HotbarService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InformationHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public InformationHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onInformation(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar information = hotbarManager.getHotbar("INFORMATION");

            if (information.isEnable() && information.isHotbarItem(event.getItem())) {
                event.setCancelled(true);

                Player player = event.getPlayer();

                for (String info : HotbarService.INFORMATION) {
                    player.sendMessage(ChatUtil.placeholder(player, ChatUtil.replaced(player, info), true));
                }
            }
        }
    }
}
