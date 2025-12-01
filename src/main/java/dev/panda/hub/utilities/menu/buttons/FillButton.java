package dev.panda.hub.utilities.menu.buttons;

import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FillButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return ConfigService.MENU_FILL_BUTTON_ICON;
    }
}
