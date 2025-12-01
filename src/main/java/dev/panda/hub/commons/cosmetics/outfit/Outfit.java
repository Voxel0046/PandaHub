package dev.panda.hub.commons.cosmetics.outfit;

import dev.panda.hub.PandaHub;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
public class Outfit {

    private final String name;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public Outfit(String name) {
        this.name = name;
    }

    public String getPermission() {
        return "pandahub.cosmetics.outfit." + name;
    }

    public void equipAll(Player player) {
        player.getInventory().setArmorContents(null);

        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);

        player.updateInventory();
    }

    public void unEquipAll(Player player) {
        player.getInventory().setArmorContents(null);
        player.updateInventory();
    }

    public static Outfit getOutfit(String name) {
        return PandaHub.get().getModuleService().getManagerModule().getOutfitManager().getOutfits().get(name);
    }
}
