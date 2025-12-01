package dev.panda.hub.commons.cosmetics.outfit.menu.buttons.chestplate;

import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitChestplateMenu;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitEditorMenu;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitHelmetMenu;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class OutfitChestplateButton extends Button {

    private Outfit outfit;

    @Override
    public ItemStack getButtonItem(Player player) {
        return outfit.getChestplate().clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.hasPermission(outfit.getPermission())) {
            playSuccess(player);
            player.getInventory().setChestplate(getButtonItem(player));
            new OutfitEditorMenu().openMenu(player);
        }
        else if (player.hasPermission("pandahub.cosmetics.outfit.*")) {
            playSuccess(player);
            player.getInventory().setChestplate(getButtonItem(player));
            new OutfitEditorMenu().openMenu(player);
        }
        else {
            playFail(player);
        }
    }
}
