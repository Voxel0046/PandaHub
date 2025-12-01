package dev.panda.hub.commands.network;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.services.impl.ConfigService;

public class WebsiteCommand extends BaseCommand {

	@Command(name = "website", aliases = {"web"}, inGameOnly = false)
	@Override
	public void onCommand(CommandArgs command) {
		for (String key : LangService.WEBSITE) {
			ChatUtil.sendMessage(command.getSender(), key.replace("%WEBSITE%", ConfigService.WEBSITE));
		}
	}
}
