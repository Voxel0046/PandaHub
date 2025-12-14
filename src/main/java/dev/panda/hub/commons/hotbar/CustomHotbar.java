package dev.panda.hub.commons.hotbar;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter @Setter
public class CustomHotbar {

    private final String name;
    private boolean enable;
    private ItemStack item;
    private int slot;
    private boolean head;
    private String playerHead; // for %player% heads
    private List<String> commands;

    public CustomHotbar(String name) {
        this.name = name;
    }
}
