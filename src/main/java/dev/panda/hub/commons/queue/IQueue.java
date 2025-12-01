package dev.panda.hub.commons.queue;

import org.bukkit.entity.Player;

public interface IQueue {

    String getQueueSystem();

    String getServer(Player player);

    int getPosition(Player player);

    int getSize(Player player);

    int getPlayersInQueue(String queueName);

    boolean isInQueue(Player player);

    void addToQueue(Player player, String queueName);
}
