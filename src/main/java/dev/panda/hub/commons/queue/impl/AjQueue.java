package dev.panda.hub.commons.queue.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.queue.IQueue;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import us.ajg0702.queue.api.AjQueueAPI;
import us.ajg0702.queue.api.spigot.MessagedResponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

public class AjQueue implements IQueue {

    private final Map<UUID, String> serverCache = new ConcurrentHashMap<>();
    private final Map<UUID, Integer> positionCache = new ConcurrentHashMap<>();
    private final Map<String, Integer> sizeCache = new ConcurrentHashMap<>();
    private final Map<UUID, Boolean> inQueueCache = new ConcurrentHashMap<>();

    // used for tablist
    @Getter private final Map<String, Boolean> serverStatus = new ConcurrentHashMap<>();

    public AjQueue() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(PandaHub.get(), this::refresh, 20L, 40L);
    }

    private void refresh() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID uuid = player.getUniqueId();

            CompletableFuture.runAsync(() -> {
                try {
                    MessagedResponse<String> response = AjQueueAPI.getSpigotInstance()
                            .getQueueName(uuid)
                            .get(5, TimeUnit.SECONDS);
                    serverCache.put(uuid, response.getResponse());
                } catch (InterruptedException | ExecutionException | TimeoutException ignored) {}
            });

            CompletableFuture.runAsync(() -> {
                try {
                    MessagedResponse<Integer> response = AjQueueAPI.getSpigotInstance()
                            .getPosition(uuid)
                            .get(5, TimeUnit.SECONDS);
                    positionCache.put(uuid, response.getResponse());
                } catch (InterruptedException | ExecutionException | TimeoutException ignored) {}
            });

            CompletableFuture.runAsync(() -> {
                try {
                    Boolean response = AjQueueAPI.getSpigotInstance()
                            .isInQueue(uuid)
                            .get(5, TimeUnit.SECONDS);
                    inQueueCache.put(uuid, response);
                } catch (InterruptedException | ExecutionException | TimeoutException ignored) {
                }
            });

            String server = getServer(player);
            if (server != null) {
                CompletableFuture.runAsync(() -> {
                    try {
                        Integer response = AjQueueAPI.getSpigotInstance()
                                .getPlayersInQueue(server)
                                .get(5, TimeUnit.SECONDS);
                        sizeCache.put(server, response);
                    } catch (InterruptedException | ExecutionException | TimeoutException ignored) {}
                });
            }


        }
    }

    @Override
    public String getQueueSystem() {
        return "AjQueue";
    }

    @Override
    public String getServer(Player player) {
        return serverCache.getOrDefault(player.getUniqueId(), "None");
    }

    @Override
    public int getPosition(Player player) {
        return positionCache.getOrDefault(player.getUniqueId(), 0);
    }

    @Override
    public int getSize(Player player) {
        String server = getServer(player);
        return sizeCache.getOrDefault(server, 0);
    }

    @Override
    public int getPlayersInQueue(String queueName) {
        return sizeCache.getOrDefault(queueName, 0);
    }

    @Override
    public boolean isInQueue(Player player) {
        return inQueueCache.getOrDefault(player.getUniqueId(), false);
    }

    @Override
    public void addToQueue(Player player, String queueName) {
        AjQueueAPI.getSpigotInstance().addToQueue(player.getUniqueId(), queueName);
    }
}
