package dev.panda.hub.commands.spawn;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.spawn.SpawnManager;
import dev.panda.hub.services.impl.LangService;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends BaseCommand {

	private final SpawnManager spawnManager;

	public SetSpawnCommand() {
		this.spawnManager = PandaHub.get().getModuleService().getManagerModule().getSpawnManager();
	}

	@Command(name = "setspawn", permission = "pandahub.command.setspawn")
	@Override
	public void onCommand(CommandArgs command) {
		Player player = command.getPlayer();

		spawnManager.setLocation(player.getLocation());
		ChatUtil.sendMessage(player, LangService.SPAWN_SET
				.replace("%WORLD%", player.getWorld().getName())
				.replace("%X%", String.valueOf(Math.round(player.getLocation().getX())))
				.replace("%Y%", String.valueOf(Math.round(player.getLocation().getY())))
				.replace("%Z%", String.valueOf(Math.round(player.getLocation().getZ()))));
	}
}
