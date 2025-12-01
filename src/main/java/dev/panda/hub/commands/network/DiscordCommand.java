package dev.panda.hub.commands.network;

import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;

public class DiscordCommand extends BaseCommand {

	@Command(name = "discord", aliases = {"dc"}, inGameOnly = false)
	@Override
	public void onCommand(CommandArgs command) {
		for (String key : LangService.DISCORD) {
			ChatUtil.sendMessage(command.getSender(), key.replace("%DISCORD%", ConfigService.DISCORD));
		}
	}
}
