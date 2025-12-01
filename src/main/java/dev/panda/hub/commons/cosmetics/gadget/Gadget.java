package dev.panda.hub.commons.cosmetics.gadget;

import dev.panda.hub.profile.Profile;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.HotbarService;
import dev.panda.hub.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.activated.core.utilities.chat.CC;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor @Getter
public enum Gadget {

    BOW_TELEPORT("Bow Teleport", "&a&lBow Teleport", Material.BOW),
    SNOWMAN("Snowman", "&b&lSnowball", CompatibleMaterial.SNOW_BALL.getMaterial()),
    RIDER("Rider", "&6&lRider", Material.SADDLE),
    FIREWORK("Firework", "&c&lFirework", Material.FIREWORK),
    GRAPPLING_HOOK("Grappling Hook", "&d&lGrappling Hook", Material.FISHING_ROD);

    private final String name;
    private final String displayName;
    private final Material material;

    public int getSlot() {
        return HotbarService.GADGET_SLOT;
    }

    public String getPermission() {
        return "pandahub.cosmetics.gadget." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, Profile profile) {
        boolean isEquipped = profile.getGadget() != null && profile.getGadget().equals(this);
        boolean hasPermission = player.hasPermission(getPermission()) || player.hasPermission("pandahub.cosmetics.gadget.*");

        if (isEquipped) {
            return new ItemBuilder(material)
                    .name(CosmeticService.GADGET_EQUIPPED_DISPLAYNAME_BUTTON
                            .replace("%GADGET_NAME%", name)
                            .replace("%GADGET_DISPLAYNAME%", displayName))
                    .lore(CosmeticService.GADGET_EQUIPPED_DESCRIPTION_BUTTON)
                    .build();
        } else if (hasPermission) {
            return new ItemBuilder(material)
                    .name(CosmeticService.GADGET_ALLOWED_DISPLAYNAME_BUTTON
                            .replace("%GADGET_NAME%", name)
                            .replace("%GADGET_DISPLAYNAME%", displayName))
                    .lore(CosmeticService.GADGET_ALLOWED_DESCRIPTION_BUTTON)
                    .build();
        } else {
            return new ItemBuilder(material)
                    .name(CosmeticService.GADGET_DENIED_DISPLAYNAME_BUTTON
                            .replace("%GADGET_NAME%", name)
                            .replace("%GADGET_DISPLAYNAME%", displayName))
                    .lore(CosmeticService.GADGET_DENIED_DESCRIPTION_BUTTON)
                    .build();
        }
    }

    public ItemStack getGadgetItem() {
        return new ItemBuilder(material)
                .name(displayName)
                .enchant(this == Gadget.BOW_TELEPORT, Enchantment.ARROW_INFINITE, 1)
                .unbreakable(true)
                .build();
    }

    public void giveGadgetItem(Player player) {
        if (this == Gadget.BOW_TELEPORT) {
            player.getInventory().setItem(27, new ItemStack(Material.ARROW));
        } else {
            player.getInventory().remove(new ItemStack(Material.ARROW));
        }
        player.getInventory().setItem(getSlot() - 1, getGadgetItem());
    }

    public void removeGadgetItem(Player player) {
        player.getInventory().setItem(getSlot() - 1, null);
        player.getInventory().setItem(27, null);
        player.getInventory().remove(new ItemStack(Material.ARROW));
        player.updateInventory();
    }
}
