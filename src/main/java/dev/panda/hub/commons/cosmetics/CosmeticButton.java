package dev.panda.hub.commons.cosmetics;

import dev.panda.hub.commons.cosmetics.chat_color.menu.ChatColorMenu;
import dev.panda.hub.commons.cosmetics.gadget.menu.GadgetMenu;
import dev.panda.hub.commons.cosmetics.message_color.menu.MessageColorMenu;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitEditorMenu;
import dev.panda.hub.commons.cosmetics.trail.menu.TrailMenu;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class CosmeticButton extends Button {

    private final Cosmetic cosmetic;

    @Override
    public ItemStack getButtonItem(Player player) {
        return cosmetic.getIcon(player);
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (cosmetic.getName().equalsIgnoreCase("OUTFIT")) {
            playSuccess(player);
            new OutfitEditorMenu().openMenu(player);
        }
        else if (cosmetic.getName().equalsIgnoreCase("TRAIL")) {
            playSuccess(player);
            new TrailMenu().openMenu(player);
        }
        else if (cosmetic.getName().equalsIgnoreCase("GADGET")) {
            playSuccess(player);
            new GadgetMenu().openMenu(player);
        }
        else if (cosmetic.getName().equalsIgnoreCase("MESSAGE_COLOR")) {
            playSuccess(player);
            new MessageColorMenu().openMenu(player);
        }
        else if (cosmetic.getName().equalsIgnoreCase("CHAT_COLOR")) {
            playSuccess(player);
            new ChatColorMenu().openMenu(player);
        }
    }
}
