package dev.panda.hub.commons.hotbar;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public class Hotbar {

    private final String name;
    private boolean enable;
    private ItemStack item;
    private int slot;
    private boolean head;

    public Hotbar(String name) {
        this.name = name;
    }

    public boolean isHotbarItem(ItemStack itemStack) {
        return (itemStack != null)
                && (itemStack.getType() != Material.AIR)
                && (itemStack.hasItemMeta())
                && (itemStack.getItemMeta().getDisplayName() != null)
                && itemStack.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName());
    }
}
