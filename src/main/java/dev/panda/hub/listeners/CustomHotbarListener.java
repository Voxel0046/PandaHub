package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.hotbar.CustomHotbar;
import dev.panda.hub.commons.hotbar.CustomHotbarManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomHotbarListener implements Listener {

	private CustomHotbarManager customHotbarManager;

	public CustomHotbarListener(PandaHub plugin) {
		this.customHotbarManager = PandaHub.get().getModuleService().getManagerModule().getCustomHotbarManager();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	private void onJoin(PlayerJoinEvent event) {

		new BukkitRunnable() {
			@Override
			public void run() {
				customHotbarManager.setCustomHotbar(event.getPlayer());
			}
		}.runTaskLater(PandaHub.get(), 4L);
	}

	@EventHandler
	private void onCustomHotbarItem(PlayerInteractEvent event) {
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			ItemStack item = event.getItem();

			if (item == null || item.getType().equals(Material.AIR)) return;

			CustomHotbar customHotbar = customHotbarManager.getCustomHotbar(item);

			if (customHotbar == null) return;

			if (customHotbar.isEnable()) {
				Player player = event.getPlayer();

				for (String command : customHotbar.getCommands()) {
					command = command.replace("%PLAYER%", player.getName());

					if (command.contains("[PLAYER]")) {
						player.performCommand(command.replace("[PLAYER] ", ""));
					}
					else {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
					}
				}
			}
		}
	}
}
