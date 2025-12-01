package dev.panda.hub.utilities.skull;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface SkullVersion {

    ItemStack createCustomSkull(String texture, String displayName, List<String> lore);
}
