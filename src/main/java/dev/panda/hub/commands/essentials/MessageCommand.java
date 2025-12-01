package dev.panda.hub.commands.essentials;

import dev.panda.hub.PandaHub;
import dev.panda.hub.cache.CommunicationCache;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.rank.RankManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MessageCommand extends BaseCommand {

	public final ProfileManager profileManager;
	private final RankManager rankManager;

	public MessageCommand() {
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
		this.rankManager = PandaHub.get().getModuleService().getManagerModule().getRankManager();
	}

	@Command(name = "message", aliases = {"msg", "m", "tell"})
	@Override
	public void onCommand(CommandArgs command) {
		Player player = command.getPlayer();
		String[] args = command.getArgs();

		if (args.length < 2) {
			ChatUtil.sendMessage(player,"&cUsage: /" + command.getLabel() + " <player> <message>");
			return;
		}

		Player target = Bukkit.getPlayer(args[0]);

		if (target == null) {
			ChatUtil.sendMessage(player, "&cPlayer '" + args[0] + "' not found.");
			return;
		}

		Profile playerProfile = profileManager.getProfile(player.getUniqueId());
		Profile targetProfile = profileManager.getProfile(target.getUniqueId());

		String message = StringUtils.join(args, ' ', 1, args.length);

		ChatUtil.sendMessage(player, ConfigService.CUSTOM_MESSAGE_FORMAT_TO
				.replace("%TARGET%", target.getName())
				.replace("%RANK_PREFIX%", rankManager.getRank().getRankPrefix(target.getUniqueId()))
				.replace("%MESSAGE_COLOR%", playerProfile.getTranslateMessageColor())
				.replace("%MESSAGE%", message));
		ChatUtil.sendMessage(target, ConfigService.CUSTOM_MESSAGE_FORMAT_FROM
				.replace("%PLAYER%", player.getName())
				.replace("%RANK_PREFIX%", rankManager.getRank().getRankPrefix(player.getUniqueId()))
				.replace("%MESSAGE_COLOR%", targetProfile.getTranslateMessageColor())
				.replace("%MESSAGE%", message));

		CommunicationCache.add(player.getUniqueId(), target.getUniqueId());
		CommunicationCache.add(target.getUniqueId(), player.getUniqueId());
	}
}
