package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.listeners.hotbar.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class HotbarListener implements Listener {

	public HotbarListener(PandaHub plugin) {
		new CosmeticHotbarListener(plugin);
		new EnderButtHotbarListener(plugin);
		new HideShowHotbarListener(plugin);
		new InformationHotbarListener(plugin);
		new LobbySelectorHotbarListener(plugin);
		new ServerSelectorHotbarListener(plugin);
		new PvPModeHotbarListener(plugin);

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	private void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		new BukkitRunnable() {
			@Override
			public void run() {
				PandaHub.get().getModuleService().getManagerModule().getHotbarManager().setHotbar(player);
			}
		}.runTaskLater(PandaHub.get(), 4L);
	}

}
