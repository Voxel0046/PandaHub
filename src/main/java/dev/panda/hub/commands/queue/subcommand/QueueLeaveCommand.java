package dev.panda.hub.commands.queue.subcommand;

import dev.panda.hub.services.impl.QueueService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.commons.queue.QueueHandler;
import org.bukkit.entity.Player;

public class QueueLeaveCommand extends BaseCommand {

    @Command(name = "queue.leave")
    @Override
    public void onCommand(CommandArgs command) {
        Player player = command.getPlayer();

        if (QueueHandler.getQueue(player) == null) {
            ChatUtil.sendMessage(player, QueueService.QUEUE_NOT_IN);
            return;
        }

        ChatUtil.sendMessage(player, QueueService.QUEUE_LEAVE.replace("%QUEUE_NAME%", QueueHandler.getQueueName(player)));
        QueueHandler.getQueue(player).removeEntry(player);
    }
}
