package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TrailListener implements Listener {

	private final ProfileManager profileManager;

	public TrailListener(PandaHub plugin) {
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	private void onPlayerMove(PlayerMoveEvent event) {
		Location from = event.getFrom();
		Location to = event.getTo();

		if (from.getBlockX() == to.getBlockX() && from.getBlockY() == to.getBlockY() && from.getBlockZ() == to.getBlockZ()) return;

		Player player = event.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.getTrail() == null || profile.isPvpMode()) return;

		profile.getTrail().playEffect(player);
	}
}
