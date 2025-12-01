package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.CooldownUtil;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.compatibility.sound.CompatibleSound;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.concurrent.TimeUnit;

public class HideShowHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public HideShowHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onHidePlayer(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar hidePlayer = hotbarManager.getHotbar("HIDE_PLAYER");

            if (hidePlayer.isEnable() && hidePlayer.isHotbarItem(event.getItem())) {
                event.setCancelled(true);

                Hotbar showPlayer = hotbarManager.getHotbar("SHOW_PLAYER");

                if (showPlayer.isEnable()) {
                    Player player = event.getPlayer();

                    if (CooldownUtil.hasCooldown("HIDE_AND_SHOW", player)) {
                        player.sendMessage(ChatUtil.translate(ConfigService.HIDE_AND_SHOW_COOLDOWN_MESSAGE
                                .replace("%TIME%", String.valueOf(CooldownUtil.getCooldown("HIDE_AND_SHOW", player)))));
                        return;
                    }

                    for (Player online : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(online);
                    }

                    CompatibleSound.CLICK.play(player);
                    player.getInventory().setItem(showPlayer.getSlot() - 1, showPlayer.getItem());
                    ChatUtil.sendMessage(player, LangService.HIDE_PLAYER);

                    if (ConfigService.HIDE_AND_SHOW_COOLDOWN_ENABLE) {
                        CooldownUtil.setCooldown("HIDE_AND_SHOW", player, TimeUnit.SECONDS.toMillis(ConfigService.HIDE_AND_SHOW_COOLDOWN_TIME));
                    }
                }
            }
        }
    }

    @EventHandler
    private void onShowPlayer(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar showPlayer = hotbarManager.getHotbar("SHOW_PLAYER");

            if (showPlayer.isEnable() && showPlayer.isHotbarItem(event.getItem())) {
                event.setCancelled(true);

                Hotbar hidePlayer = hotbarManager.getHotbar("HIDE_PLAYER");

                if (hidePlayer.isEnable()) {
                    Player player = event.getPlayer();

                    if (CooldownUtil.hasCooldown("HIDE_AND_SHOW", player)) {
                        player.sendMessage(ChatUtil.translate(ConfigService.HIDE_AND_SHOW_COOLDOWN_MESSAGE
                                .replace("%TIME%", String.valueOf(CooldownUtil.getCooldown("HIDE_AND_SHOW", player)))));
                        return;
                    }

                    for (Player online : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(online);
                    }

                    CompatibleSound.CLICK.play(player);
                    player.getInventory().setItem(hidePlayer.getSlot() - 1, hidePlayer.getItem());
                    ChatUtil.sendMessage(player, LangService.SHOW_PLAYER);

                    if (ConfigService.HIDE_AND_SHOW_COOLDOWN_ENABLE) {
                        CooldownUtil.setCooldown("HIDE_AND_SHOW", player, TimeUnit.SECONDS.toMillis(ConfigService.HIDE_AND_SHOW_COOLDOWN_TIME));
                    }
                }
            }
        }
    }
}
