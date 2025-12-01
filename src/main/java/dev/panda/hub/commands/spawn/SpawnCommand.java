package dev.panda.hub.commands.spawn;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.spawn.SpawnManager;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.LangService;
import org.bukkit.entity.Player;

public class SpawnCommand extends BaseCommand {

	private final ProfileManager profileManager;
	private final SpawnManager spawnManager;

	public SpawnCommand() {
		this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
		this.spawnManager = PandaHub.get().getModuleService().getManagerModule().getSpawnManager();
	}

	@Command(name = "spawn")
	@Override
	public void onCommand(CommandArgs command) {
		Player player = command.getPlayer();
		Profile profile = profileManager.getProfile(player.getUniqueId());

		if (profile.isPvpMode()) {
			ChatUtil.sendMessage(player, "&cYou can't use this command in PvP Mode.");
			return;
		}

		spawnManager.toSpawn(player, true);
		ChatUtil.sendMessage(player, LangService.SPAWN_SPAWNED);
	}
}
