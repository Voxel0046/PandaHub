package dev.panda.hub.utilities.menu.pagination;

import dev.panda.hub.utilities.item.ItemBuilder;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class PageButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        ItemBuilder itemBuilder = new ItemBuilder(Material.STAINED_GLASS_PANE);
        itemBuilder.data((short) 15);
        itemBuilder.name("&r ");

        return itemBuilder.build();
    }

    @Override
    public void clicked(Player player, int i, ClickType clickType, int hb) {
    }
}
