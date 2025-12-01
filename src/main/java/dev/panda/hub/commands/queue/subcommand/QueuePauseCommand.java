package dev.panda.hub.commands.queue.subcommand;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.commons.queue.Queue;
import dev.panda.hub.commons.queue.QueueHandler;
import dev.panda.hub.services.impl.QueueService;
import org.bukkit.command.CommandSender;

public class QueuePauseCommand extends BaseCommand {

    @Command(name = "queue.pause", permission = "pandahub.command.queue.pause", inGameOnly = false)
    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        String[] args = command.getArgs();

        if (args.length < 1) {
            ChatUtil.sendMessage(sender, "&cUsage: /queue pause <queue>");
            return;
        }

        Queue queue = QueueHandler.getQueue(args[0]);

        if (queue == null) {
            ChatUtil.sendMessage(sender, QueueService.QUEUE_NOT_FOUND
                    .replace("%QUEUE_NAME%", args[0]));
            return;
        }

        if (queue.isPaused()) {
            ChatUtil.sendMessage(sender, QueueService.QUEUE_UNPAUSED
                    .replace("%QUEUE_NAME%", queue.getServer()));
        }
        else {
            ChatUtil.sendMessage(sender, QueueService.QUEUE_PAUSED
                    .replace("%QUEUE_NAME%", queue.getServer()));
        }
        queue.setPaused(!queue.isPaused());
    }
}
