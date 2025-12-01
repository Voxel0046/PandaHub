package dev.panda.hub.utilities.menu.buttons;

import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class BackButton extends Button {

    private final Menu back;

    @Override
    public ItemStack getButtonItem(Player player) {
        return ConfigService.MENU_BACK_BUTTON_ICON;
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
        playNeutral(player);
        this.back.openMenu(player);
    }
}
