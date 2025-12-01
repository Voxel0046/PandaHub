package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SkullListener implements Listener {

    public SkullListener(PandaHub plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onHeadClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            BlockState state = event.getClickedBlock().getState();

            if (state instanceof Skull) {
                Skull skull = (Skull) state;

                event.getPlayer().sendMessage(ChatUtil.translate(LangService.SKULL
                        .replace("%SKULL_NAME%", skull.hasOwner() ? skull.getOwner() : skull.getSkullType().name())));
            }
        }
    }
}
