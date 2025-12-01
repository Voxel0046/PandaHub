package dev.panda.hub.commons.hotbar;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class CustomHotbarManager {

    @Getter
    private final Map<ItemStack, CustomHotbar> customHotbars;
    private final FileConfig customHotbarConfig;

    public CustomHotbarManager() {
        this.customHotbars = Maps.newHashMap();
        this.customHotbarConfig = PandaHub.get().getModuleService().getFileModule().getFile("custom-hotbar");
    }

    public void load() {
        customHotbars.clear();

        ConfigurationSection section = customHotbarConfig.getConfiguration();

        if (section == null) return;

        for (String customHotbarName : section.getKeys(false)) {
            CustomHotbar customHotbar = new CustomHotbar(customHotbarName);
            customHotbar.setEnable(section.getBoolean(customHotbarName + ".ENABLE"));
            customHotbar.setHead(section.getBoolean(customHotbarName + ".ICON.HEAD.ENABLE"));

            ItemStack itemStack;

            if (customHotbar.isHead()) {
                if (!section.getString(customHotbarName + ".ICON.HEAD.CUSTOM").isEmpty()) {
                    itemStack = PandaHub.get()
                            .getModuleService()
                            .getManagerModule()
                            .getSkullManager()
                            .getVersion()
                            .createCustomSkull(
                                    section.getString(customHotbarName + ".ICON.HEAD.CUSTOM"),
                                    section.getString(customHotbarName + ".ICON.DISPLAYNAME"),
                                    section.getStringList(customHotbarName + ".ICON.DESCRIPTION")
                    );
                }
                else if (!section.getString(customHotbarName + ".ICON.HEAD.PLAYER").isEmpty()) {
                    itemStack = ItemBuilder.createSkull(
                            section.getString(customHotbarName + ".ICON.HEAD.PLAYER"),
                            section.getString(customHotbarName + ".ICON.DISPLAYNAME"),
                            section.getStringList(customHotbarName + ".ICON.DESCRIPTION")
                    );
                }
                else {
                    itemStack = new ItemBuilder(section.getString(customHotbarName + ".ICON.MATERIAL"))
                            .data(section.getInt(customHotbarName + ".ICON.DATA"))
                            .name(section.getString(customHotbarName + ".ICON.DISPLAYNAME"))
                            .lore(section.getStringList(customHotbarName + ".ICON.DESCRIPTION"))
                            .amount(section.getInt(customHotbarName + ".ICON.AMOUNT"))
                            .enchant(section.getBoolean(customHotbarName + ".ICON.ENCHANTED"))
                            .build();
                }
            }
            else {
                itemStack = new ItemBuilder(section.getString(customHotbarName + ".ICON.MATERIAL"))
                        .data(section.getInt(customHotbarName + ".ICON.DATA"))
                        .name(section.getString(customHotbarName + ".ICON.DISPLAYNAME"))
                        .lore(section.getStringList(customHotbarName + ".ICON.DESCRIPTION"))
                        .amount(section.getInt(customHotbarName + ".ICON.AMOUNT"))
                        .enchant(section.getBoolean(customHotbarName + ".ICON.GLOW"))
                        .build();
            }

            customHotbar.setItem(itemStack);
            customHotbar.setSlot(section.getInt(customHotbarName + ".ICON.SLOT"));
            customHotbar.setCommands(section.getStringList(customHotbarName + ".COMMANDS"));
            customHotbars.put(itemStack, customHotbar);
        }
    }

    public CustomHotbar getCustomHotbar(ItemStack itemStack) {
        return customHotbars.get(itemStack);
    }

    public void setCustomHotbar(Player player) {
        for (CustomHotbar customHotbar : customHotbars.values()) {
            if (customHotbar.isEnable()) {
                player.getInventory().setItem(customHotbar.getSlot() - 1, customHotbar.getItem());
            }
        }
    }

    public void reload() {
        for (Player online : Bukkit.getOnlinePlayers()) {
            setCustomHotbar(online);
        }
    }
}
