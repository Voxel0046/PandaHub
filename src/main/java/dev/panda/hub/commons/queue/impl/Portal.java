package dev.panda.hub.commons.queue.impl;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.commons.queue.IQueue;
import me.joeleoli.portal.shared.queue.Queue;
import me.signatured.ezqueuespigot.EzQueueAPI;
import org.bukkit.entity.Player;

public class Portal implements IQueue {

    @Override
    public String getQueueSystem() {
        return "Portal";
    }

    @Override
    public String getServer(Player player) {
        Queue queue = Queue.getByPlayer(player.getUniqueId());
        return queue.getName();
    }

    @Override
    public int getPosition(Player player) {
        Queue queue = Queue.getByPlayer(player.getUniqueId());
        return queue.getPosition(player.getUniqueId());
    }

    @Override
    public int getSize(Player player) {
        Queue queue = Queue.getByPlayer(player.getUniqueId());
        return queue.getPlayers().size();
    }

    @Override
    public int getPlayersInQueue(String queueName) {
        Queue queue = Queue.getByName(queueName);
        return queue == null ? -1 : queue.getPlayers().size();
    }

    @Override
    public boolean isInQueue(Player player) {
        Queue queue = Queue.getByPlayer(player.getUniqueId());
        return queue != null;
    }

    @Override
    public void addToQueue(Player player, String queueName) {
        Queue queue = Queue.getByName(queueName);

        if (queue == null) {
            player.sendMessage(ChatUtil.translate("&cQueue '" + queueName + "' does not exist."));
            return;
        }

        queue.sendPlayer(player, queueName);
    }
}
