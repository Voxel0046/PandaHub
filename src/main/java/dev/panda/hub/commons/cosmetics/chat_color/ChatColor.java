package dev.panda.hub.commons.cosmetics.chat_color;

import dev.panda.hub.profile.Profile;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor @Getter
public enum ChatColor {

    DARK_RED("Dark Red", "&4", 14),
    RED("Red", "&c", 14),
    ORANGE("Orange", "&6", 1),
    YELLOW("Yellow", "&e", 4),
    DARK_GREEN("Dark Green", "&2", 13),
    GREEN("Green", "&a", 5),
    DARK_BLUE("Dark Blue", "&1", 11),
    BLUE("Blue", "&9", 9),
    DARK_AQUA("Dark Aqua", "&3", 9),
    AQUA("Aqua", "&b", 3),
    PURPLE("Purple", "&5", 10),
    PINK("Pink", "&d", 6),
    DARK_GRAY("Dark Gray", "&8", 7),
    BLACK("Black", "&0", 15);

    private final String name;
    private final String color;
    private final int data;

    public String getDisplayName() {
        return color + org.bukkit.ChatColor.BOLD + name;
    }

    public String getPermission() {
        return "pandahub.cosmetics.chat_color." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, Profile profile) {
        boolean isEquipped = profile.getChatColor() != null && profile.getChatColor().equals(this);
        boolean hasPermission = player.hasPermission(getPermission()) || player.hasPermission("pandahub.cosmetics.chat_color.*");

        if (isEquipped) {
            return new ItemBuilder(CompatibleMaterial.WOOL.getMaterial())
                    .name(CosmeticService.CHAT_COLOR_EQUIPPED_DISPLAYNAME_BUTTON
                            .replace("%CHAT_COLOR_NAME%", name)
                            .replace("%CHAT_COLOR_DISPLAYNAME%", getDisplayName()))
                    .data(data)
                    .lore(CosmeticService.CHAT_COLOR_EQUIPPED_DESCRIPTION_BUTTON)
                    .build();
        } else if (hasPermission) {
            return new ItemBuilder(CompatibleMaterial.WOOL.getMaterial())
                    .name(CosmeticService.CHAT_COLOR_ALLOWED_DISPLAYNAME_BUTTON
                            .replace("%CHAT_COLOR_NAME%", name)
                            .replace("%CHAT_COLOR_DISPLAYNAME%", getDisplayName()))
                    .data(data)
                    .lore(CosmeticService.CHAT_COLOR_ALLOWED_DESCRIPTION_BUTTON)
                    .build();
        } else {
            return new ItemBuilder(CompatibleMaterial.WOOL.getMaterial())
                    .name(CosmeticService.CHAT_COLOR_DENIED_DISPLAYNAME_BUTTON
                            .replace("%CHAT_COLOR_NAME%", name)
                            .replace("%CHAT_COLOR_DISPLAYNAME%", getDisplayName()))
                    .data(data)
                    .lore(CosmeticService.CHAT_COLOR_DENIED_DESCRIPTION_BUTTON)
                    .build();
        }
    }
}
