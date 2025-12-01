package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.rank.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	private final ProfileManager profileManager;
	private final RankManager rankManager;

	public ChatListener(PandaHub plugin) {
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
		this.rankManager = PandaHub.get().getModuleService().getManagerModule().getRankManager();
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	private void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (ConfigService.DISABLE_CHAT) {
			if (!player.hasPermission("pandahub.chat.bypass")) {
				event.setCancelled(true);
				ChatUtil.sendMessage(player, LangService.DISABLE_CHAT);
				return;
			}
		}

		if (ConfigService.CUSTOM_CHAT_ENABLE) {
			event.setCancelled(true);

			Profile profile = profileManager.getProfile(player.getUniqueId());
			String message = player.hasPermission("pandahub.chat.color") ? ChatUtil.translate(event.getMessage()) : ChatUtil.strip(event.getMessage());

			String format = ConfigService.CUSTOM_CHAT_FORMAT
					.replace("%PLAYER%", player.getName())
					.replace("%MESSAGE%", message)
					.replace("%CHAT_COLOR%", profile.getTranslateChatColor())
					.replace("%RANK%", rankManager.getRank().getRankName(player.getUniqueId()))
					.replace("%RANK_PREFIX%", rankManager.getRank().getRankPrefix(player.getUniqueId()))
					.replace("%RANK_SUFFIX%", rankManager.getRank().getRankSuffix(player.getUniqueId()))
					.replace("%RANK_COLOR%", rankManager.getRank().getRankColor(player.getUniqueId()));

			for (Player recipient : event.getRecipients()) {
				recipient.sendMessage(ChatUtil.placeholder(player, format, true));
			}
		}
	}
}
