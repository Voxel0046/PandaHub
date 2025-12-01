package dev.panda.hub.commons.queue;

import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.QueueService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.ServerUtil;
import dev.panda.hub.utilities.TaskUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

@Getter @Setter
public class Queue {

    private String server;
    private List<Player> playerList;
    private Map<Player, BukkitTask> taskMap;
    private boolean paused;

    public Queue(String server) {
        this.server = server;
        this.playerList = new ArrayList<>();
        this.taskMap = new HashMap<>();
        this.paused = false;

        TaskUtil.runTaskTimerAsynchronously(() -> {
            List<Player> list = new ArrayList<>(this.playerList);

            for (Player player : list) {
                if (player.isOnline()) {
                    for (String lines : QueueService.QUEUE_POSITION) {
                        ChatUtil.sendMessage(player, lines
                                .replace("%QUEUE_POSITION%", String.valueOf(getPosition(player)))
                                .replace("%QUEUE_SIZE%", String.valueOf(getSize()))
                                .replace("%QUEUE_NAME%", server)
                                .replace("%STORE%", ConfigService.STORE));
                    }
                }
                else {
                    list.remove(player);
                }
            }
        }, QueueService.QUEUE_POSITION_INTERVAL, QueueService.QUEUE_POSITION_INTERVAL);
    }

    public void addEntry(Player player) {
        if (playerList.contains(player)) return;

        if (QueueHandler.getPriority(player) == 0) {
            sendDirect(player);
            ChatUtil.sendMessage(player, QueueService.QUEUE_SEND.replace("%QUEUE_NAME%", server));
            return;
        }

        playerList.add(player);

        for (Player online : playerList) {
            int pos = playerList.indexOf(online);

            if (player != online && QueueHandler.getPriority(player) < QueueHandler.getPriority(online)) {
                Collections.swap(playerList, pos, playerList.size() - 1);
            }
        }
    }

    public void removeEntry(Player player) {
        if (playerList.isEmpty()) return;
        if (player == null || !playerList.contains(player)) return;
        playerList.remove(player);
    }

    public int getSize() {
        return playerList.size();
    }

    public Player getPlayerAt(int i) {
        return playerList.get(i);
    }

    public int getPosition(Player player) {
        return playerList.indexOf(player) + 1;
    }

    public void sendFirst() {
        if (!playerList.isEmpty()) {
            Player player = playerList.get(0).getPlayer();
            ServerUtil.sendBungeeServer(player, server);
            removeEntry(player);
        }
    }

    public void sendDirect(Player player) {
        ServerUtil.sendBungeeServer(player, server);
        removeEntry(player);
    }
}
