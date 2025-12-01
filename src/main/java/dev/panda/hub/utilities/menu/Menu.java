package dev.panda.hub.utilities.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

@Getter @Setter
public abstract class Menu {

    public static Map<String, Menu> currentlyOpenedMenus = Maps.newHashMap();
    @Getter private Map<Integer, Button> buttons = Maps.newHashMap();

    private boolean updateAfterClick = true;
    private boolean closedByMenu = false;
    private boolean placeholder;

    private ItemStack createItemStack(Player player, Button button) {
        return new ItemBuilder(button.getButtonItem(player)).build();
    }

    public void openMenu(final Player player) {
        this.buttons = this.getButtons(player);

        Menu previousMenu = Menu.currentlyOpenedMenus.get(player.getName());
        Inventory inventory = null;
        String title = this.getTitle(player);
        int size = this.getSize() == -1 ? this.size(this.buttons) : this.getSize();
        boolean update = false;

        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        if (player.getOpenInventory() != null) {
            if (previousMenu == null) {
                player.closeInventory();
            } else {
                int previousSize = player.getOpenInventory().getTopInventory().getSize();

                if (previousSize == size && player.getOpenInventory().getTopInventory().getTitle().equals(title)) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                } else {
                    previousMenu.setClosedByMenu(true);
                    ((CraftPlayer) player).getHandle().p();
                }
            }
        }

        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, title);
        }

        inventory.setContents(new ItemStack[inventory.getSize()]);

        currentlyOpenedMenus.put(player.getName(), this);

        for (Map.Entry<Integer, Button> buttonEntry : this.buttons.entrySet()) {
            inventory.setItem(buttonEntry.getKey(), createItemStack(player, buttonEntry.getValue()));
        }

        if (this.isPlaceholder()) {
            Button fillButton = Button.placeholder(ConfigService.MENU_FILL_BUTTON_ICON);

            for (int index = 0; index < size; index++) {
                if (this.buttons.get(index) == null) {
                    this.buttons.put(index, fillButton);
                    inventory.setItem(index, fillButton.getButtonItem(player));
                }
            }
        }

        if (update) {
            player.updateInventory();
        }
        else {
            player.openInventory(inventory);
        }

        this.setClosedByMenu(false);
    }

    public int size(Map<Integer, Button> buttons) {
        int highest = 0;

        for (int buttonValue : buttons.keySet()) {
            if (buttonValue > highest) {
                highest = buttonValue;
            }
        }

        return (int) (Math.ceil((highest + 1) / 9D) * 9D);
    }

    public int getSlot(int x, int y) {
        return ((9 * y) + x);
    }

    public int getSize() {
        return -1;
    }

    public Button getPlaceholderButton() {
        return null;
    }

    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getButtons(Player player);
}