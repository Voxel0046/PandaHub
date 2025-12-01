package dev.panda.hub.commons.queue;

import dev.panda.hub.commons.queue.impl.AjQueue;
import dev.panda.hub.commons.queue.impl.Default;
import dev.panda.hub.commons.queue.impl.EzQueue;
import dev.panda.hub.commons.queue.impl.Portal;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;

@Getter @Setter
public class QueueManager {

    private IQueue queue;

    public QueueManager() {
        if (Bukkit.getPluginManager().getPlugin("EzQueueSpigot") != null) {
            this.queue = new EzQueue();
        }
        else if (Bukkit.getPluginManager().getPlugin("Portal") != null) {
            this.queue = new Portal();
        }
        else if (Bukkit.getPluginManager().getPlugin("ajQueue") != null) {
            this.queue = new AjQueue();
        }
        else {
            this.queue = new Default();
        }
    }
}
