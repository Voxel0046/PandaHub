package dev.panda.hub.commands.network;

import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;

public class StoreCommand extends BaseCommand {

	@Command(name = "store", inGameOnly = false)
	@Override
	public void onCommand(CommandArgs command) {
		for (String key : LangService.STORE) {
			ChatUtil.sendMessage(command.getSender(), key.replace("%STORE%", ConfigService.STORE));
		}
	}
}
