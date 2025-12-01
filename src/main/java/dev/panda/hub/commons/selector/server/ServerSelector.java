package dev.panda.hub.commons.selector.server;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.queue.IQueue;
import dev.panda.hub.hooks.PlaceholderAPIHook;
import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ServerSelector {

    private final String name;
    private boolean head;
    private ItemStack icon;
    private int slot;
    private String permission;
    private String server;
    private boolean subServer;
    private boolean queue;
    private List<String> commands;

    public ServerSelector(String name) {
        this.name = name;
    }

    public ItemStack getCustomIcon(Player player) {
        ItemStack item = icon.clone();

        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            if (meta.hasDisplayName()) {
                String displayName = meta.getDisplayName()
                        .replace("%PLAYER%", player.getName());
                meta.setDisplayName(PlaceholderAPIHook.isPlaceholderAPI()
                        ? PlaceholderAPI.setPlaceholders(player, displayName)
                        : displayName);
            }

            if (meta.hasLore()) {
                IQueue queue = PandaHub.get().getModuleService().getManagerModule().getQueueManager().getQueue();

                List<String> description = meta.getLore().stream()
                        .map(line -> line
                                .replace("%QUEUE_SIZE%", queue == null ? "0" : String.valueOf(queue.getPlayersInQueue(server))))
                        .collect(Collectors.toList());

                meta.setLore(PlaceholderAPIHook.isPlaceholderAPI()
                        ? PlaceholderAPI.setPlaceholders(player, description)
                        : description);
            }

            item.setItemMeta(meta);
        }

        return item;
    }
}
