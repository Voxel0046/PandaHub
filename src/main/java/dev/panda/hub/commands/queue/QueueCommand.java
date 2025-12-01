package dev.panda.hub.commands.queue;

import dev.panda.hub.commands.queue.subcommand.QueueJoinCommand;
import dev.panda.hub.commands.queue.subcommand.QueueLeaveCommand;
import dev.panda.hub.commands.queue.subcommand.QueueListCommand;
import dev.panda.hub.commands.queue.subcommand.QueuePauseCommand;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import org.bukkit.entity.Player;

public class QueueCommand extends BaseCommand {

    public QueueCommand() {
        new QueueJoinCommand();
        new QueueLeaveCommand();
        new QueuePauseCommand();
        new QueueListCommand();
    }

    @Command(name = "queue")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String label = command.getLabel();

        ChatUtil.sendMessage(player, " ");
        ChatUtil.sendMessage(player," &6&lQueue Help");
        ChatUtil.sendMessage(player, " &6┃ &f/" + label + " join <queue>");
        ChatUtil.sendMessage(player, " &6┃ &f/" + label + " leave");
        ChatUtil.sendMessage(player, " &6┃ &f/" + label + " pause <queue>");
        ChatUtil.sendMessage(player, " &6┃ &f/" + label + " list");
        ChatUtil.sendMessage(player, " ");
    }
}
