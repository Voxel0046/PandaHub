package dev.panda.hub.commands.timer;

import dev.panda.hub.commands.timer.subcommand.TimerListCommand;
import dev.panda.hub.commands.timer.subcommand.TimerStartCommand;
import dev.panda.hub.commands.timer.subcommand.TimerStopCommand;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import org.bukkit.entity.Player;

public class TimerCommand extends BaseCommand {

    public TimerCommand() {
        new TimerStartCommand();
        new TimerStopCommand();
        new TimerListCommand();
    }

    @Command(name = "timer", aliases = {"customt", "ctimer", "ct"}, permission = "pandahub.command.timer", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        ChatUtil.sendMessage(player, " ");
        ChatUtil.sendMessage(player," &9&lCustom Timer Help");
        ChatUtil.sendMessage(player, " &9┃ &f/" + command.getLabel() + " start <timer> <time> <prefix>");
        ChatUtil.sendMessage(player, " &9┃ &f/" + command.getLabel() + " stop <timer>");
        ChatUtil.sendMessage(player, " &9┃ &f/" + command.getLabel() + " list");
        ChatUtil.sendMessage(player, " ");
    }
}
