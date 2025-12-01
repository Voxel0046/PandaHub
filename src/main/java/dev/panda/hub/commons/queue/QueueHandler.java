package dev.panda.hub.commons.queue;

import com.google.common.collect.Lists;
import dev.panda.hub.services.impl.QueueService;
import dev.panda.hub.utilities.TaskUtil;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.PandaHub;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;

import java.util.List;

@UtilityClass
public class QueueHandler {

    private final FileConfig queueConfig = PandaHub.get().getModuleService().getFileModule().getFile("queue");
    private final List<Queue> queues = Lists.newArrayList();

    static {
        for (String server : QueueService.QUEUE_SERVERS) {
            queues.add(new Queue(server));
        }

        TaskUtil.runTaskTimerAsynchronously(() -> {
            for (Queue queue : queues) {
                if (!queue.isPaused() && !queue.getPlayerList().isEmpty()) {
                    Player player = queue.getPlayerAt(0);

                    if (player != null && player.isOnline()) {
                        queue.sendFirst();
                    }

                    if (!queue.getTaskMap().containsKey(player)) {
                        return;
                    }

                    queue.getTaskMap().get(player).cancel();
                    queue.getTaskMap().remove(player);
                }
            }
        }, QueueService.QUEUE_DELAY, QueueService.QUEUE_DELAY);
    }

    public Queue getQueue(Player player) {
        for (Queue queue : queues) {
            if (queue.getPlayerList().contains(player)) return queue;
        }
        return null;
    }

    public Queue getQueue(String string) {
        for (Queue queue : queues) {
            if (queue.getServer().equalsIgnoreCase(string)) return queue;
        }
        return null;
    }

    public String getQueueName(Player player) {
        return getQueue(player).getServer();
    }

    public int getPriority(Player player) {
        return queueConfig.getInt("QUEUE.PRIORITY." + PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRankName(player.getUniqueId()));
    }
}
