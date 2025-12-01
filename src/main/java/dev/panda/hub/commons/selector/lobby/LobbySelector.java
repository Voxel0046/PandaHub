package dev.panda.hub.commons.selector.lobby;

import dev.panda.hub.hooks.PlaceholderAPIHook;
import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Getter @Setter
public class LobbySelector {

    private final String name;
    private boolean head;
    private ItemStack icon;
    private int slot;
    private String lobby;

    public LobbySelector(String name) {
        this.name = name;
    }

    public ItemStack getCustomIcon(Player player) {
        ItemStack item = icon.clone();

        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();

            if (meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();
                meta.setDisplayName(PlaceholderAPIHook.isPlaceholderAPI()
                        ? PlaceholderAPI.setPlaceholders(player, displayName)
                        : displayName);
            }

            if (meta.hasLore()) {
                List<String> description = meta.getLore();
                meta.setLore(PlaceholderAPIHook.isPlaceholderAPI()
                        ? PlaceholderAPI.setPlaceholders(player, description)
                        : description);
            }

            item.setItemMeta(meta);
        }
        return item;
    }
}
