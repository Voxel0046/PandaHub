package dev.panda.hub.commands.queue.subcommand;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.commons.queue.Queue;
import dev.panda.hub.commons.queue.QueueHandler;
import dev.panda.hub.services.impl.QueueService;
import org.bukkit.entity.Player;

public class QueueJoinCommand extends BaseCommand {

    @Command(name = "queue.join")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();
        String[] args = command.getArgs();

        if (args.length < 1) {
            ChatUtil.sendMessage(player, "&cUsage: /queue join <queue>");
            return;
        }

        Queue queue = QueueHandler.getQueue(args[0]);

        if (queue == null) {
            ChatUtil.sendMessage(player, QueueService.QUEUE_NOT_FOUND
                    .replace("%QUEUE_NAME%", args[0]));
            return;
        }

        if (QueueHandler.getQueue(player) != null) {
            ChatUtil.sendMessage(player, QueueService.QUEUE_LEAVE
                    .replace("%QUEUE_NAME%", queue.getServer()));
            QueueHandler.getQueue(player).removeEntry(player);
        }

        queue.addEntry(player);
        ChatUtil.sendMessage(player, QueueService.QUEUE_JOIN
                .replace("%QUEUE_NAME%", args[0]));
    }
}
