package dev.panda.hub.commands.essentials;

import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import org.bukkit.entity.Player;

public class BuildCommand extends BaseCommand {

	private final ProfileManager profileManager;

	public BuildCommand() {
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
	}

	@Command(name = "build", permission = "pandahub.command.build")
	@Override
	public void onCommand(CommandArgs command) {
		Player player = command.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isBuildMode()) {
			profile.setBuildMode(false);
			ChatUtil.sendMessage(player, LangService.BUILD_DISABLE);
		}
		else {
			profile.setBuildMode(true);
			ChatUtil.sendMessage(player, LangService.BUILD_ENABLE);
		}
	}
}
