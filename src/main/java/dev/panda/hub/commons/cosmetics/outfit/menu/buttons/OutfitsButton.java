package dev.panda.hub.commons.cosmetics.outfit.menu.buttons;

import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitsMenu;
import dev.panda.hub.services.impl.CosmeticService;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OutfitsButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return CosmeticService.OUTFIT_EDITOR_OUTFITS_BUTTON;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        OutfitsMenu outfitsMenu = new OutfitsMenu();
        outfitsMenu.openMenu(player);
    }
}
