package dev.panda.hub.commons.queue.impl;

import dev.panda.hub.commons.queue.IQueue;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.entity.Player;

public class EzQueue implements IQueue {

    @Override
    public String getQueueSystem() {
        return "EzQueue";
    }

    @Override
    public String getServer(Player player) {
        return EzQueueAPI.getQueue(player.getUniqueId());
    }

    @Override
    public int getPosition(Player player) {
        return EzQueueAPI.getPosition(player.getUniqueId());
    }

    @Override
    public int getSize(Player player) {
        return EzQueueAPI.getQueueSize(getServer(player));
    }

    @Override
    public int getPlayersInQueue(String queueName) {
        return EzQueueAPI.getQueueSize(queueName);
    }

    @Override
    public boolean isInQueue(Player player) {
        return EzQueueAPI.getQueue(player.getUniqueId()) != null;
    }

    @Override
    public void addToQueue(Player player, String queueName) {
        EzQueueAPI.addToQueue(player, queueName);
    }
}
