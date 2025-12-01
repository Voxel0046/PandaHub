package dev.panda.hub.commons.queue.impl;

import dev.panda.hub.commands.queue.QueueCommand;
import dev.panda.hub.services.impl.QueueService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.queue.IQueue;
import dev.panda.hub.commons.queue.Queue;
import dev.panda.hub.commons.queue.QueueHandler;
import dev.panda.hub.commons.queue.QueueListener;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.entity.Player;

public class Default implements IQueue {

    public Default() {
        new QueueCommand();
        new QueueListener(PandaHub.get());
    }

    @Override
    public String getQueueSystem() {
        return "Default";
    }

    @Override
    public String getServer(Player player) {
        Queue queue = QueueHandler.getQueue(player);
        return queue.getServer();
    }

    @Override
    public int getPosition(Player player) {
        Queue queue = QueueHandler.getQueue(player);
        return queue.getPosition(player);
    }

    @Override
    public int getSize(Player player) {
        Queue queue = QueueHandler.getQueue(player);
        return queue.getSize();
    }

    @Override
    public int getPlayersInQueue(String queueName) {
        Queue queue = QueueHandler.getQueue(queueName);
        return queue == null ? -1 : queue.getSize();
    }

    @Override
    public boolean isInQueue(Player player) {
        Queue queue = QueueHandler.getQueue(player);
        return queue != null;
    }

    @Override
    public void addToQueue(Player player, String queueName) {
        Queue queue = QueueHandler.getQueue(queueName);

        if (queue == null) {
            ChatUtil.sendMessage(player, QueueService.QUEUE_NOT_FOUND
                    .replace("%QUEUE_NAME%", queueName));
            return;
        }

        if (QueueHandler.getQueue(player) != null) {
            ChatUtil.sendMessage(player, QueueService.QUEUE_LEAVE
                    .replace("%QUEUE_NAME%", queue.getServer()));
            QueueHandler.getQueue(player).removeEntry(player);
        }

        queue.addEntry(player);
        ChatUtil.sendMessage(player, QueueService.QUEUE_JOIN
                .replace("%QUEUE_NAME%", queueName));
    }
}
