package dev.panda.hub.commands;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.PandaHub;
import org.bukkit.command.CommandSender;

public class PandaHubCommand extends BaseCommand {

	@Command(name = "ehub", inGameOnly = false)
	@Override
	public void onCommand(CommandArgs command) {
		CommandSender sender = command.getSender();
		String[] args = command.getArgs();
		String label = command.getLabel();

		if (args.length < 1) {
			ChatUtil.sendMessage(sender, "&cUsage: /" + label + " reload");
		}
		else if (args[0].equalsIgnoreCase("reload")) {
			if (!sender.hasPermission("ehub.reload")) {
				ChatUtil.sendMessage(sender, "&cNo permission.");
				return;
			}

			PandaHub.get().getModuleService().getServiceModule().load(true);
			PandaHub.get().getModuleService().getManagerModule().load(true);

			ChatUtil.sendMessage(sender, "&aAll files has been reloaded.");
		}
	}
}
