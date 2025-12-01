package dev.panda.hub.commands;

import dev.panda.hub.commons.pvp.menu.PvPMenu;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;

public class PvPModeCommand extends BaseCommand {

	@Command(name = "pvpmode", permission = "pandahub.command.pvpmode")
	@Override
	public void onCommand(CommandArgs command) {
		PvPMenu pvpMenu = new PvPMenu();
		pvpMenu.openMenu(command.getPlayer());
	}
}
