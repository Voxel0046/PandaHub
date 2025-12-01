package dev.panda.hub.commands.queue.subcommand;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.command.BaseCommand;
import dev.panda.hub.utilities.command.Command;
import dev.panda.hub.utilities.command.CommandArgs;
import dev.panda.hub.services.impl.QueueService;

public class QueueListCommand extends BaseCommand {

    @Command(name = "queue.list")
    @Override
    public void onCommand(CommandArgs command) {
        ChatUtil.sendMessage(command.getPlayer(), "&6Availables Queues&7: &f" + QueueService.QUEUE_SERVERS);
    }
}
