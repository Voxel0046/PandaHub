package dev.panda.hub.commons.cosmetics.outfit.menu.buttons.chestplate;

import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitChestplateMenu;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.utilities.compatibility.sound.CompatibleSound;
import dev.panda.hub.utilities.item.ItemBuilder;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.stream.Collectors;

@AllArgsConstructor
public class OutfitEquipChestplateButton extends Button {

    private final String part;

    @Override
    public ItemStack getButtonItem(Player player) {
        if (player.getInventory().getChestplate() == null) {
            return new ItemBuilder(CosmeticService.OUTFIT_EDITOR_NO_EQUIPPED_MATERIAL_BUTTON)
                    .data(CosmeticService.OUTFIT_EDITOR_NO_EQUIPPED_DATA_BUTTON)
                    .name(CosmeticService.OUTFIT_EDITOR_NO_EQUIPPED_DISPLAYNAME_BUTTON
                            .replace("%OUTFIT_PART%", part))
                    .lore(CosmeticService.OUTFIT_EDITOR_NO_EQUIPPED_DESCRIPTION_BUTTON
                            .stream().map(desc -> desc.replace("%OUTFIT_PART%", part))
                            .collect(Collectors.toList()))
                    .build();
        }
        else {
            ItemStack outfit = player.getInventory().getChestplate();
            ItemBuilder itemBuilder = new ItemBuilder(CosmeticService.OUTFIT_EDITOR_EQUIPPED_MATERIAL_BUTTON.
                    replace("%OUTFIT_MATERIAL%", outfit.getType().name()));
            itemBuilder.data(CosmeticService.OUTFIT_EDITOR_EQUIPPED_DATA_BUTTON);
            itemBuilder.name(CosmeticService.OUTFIT_EDITOR_EQUIPPED_DISPLAYNAME_BUTTON
                    .replace("%OUTFIT_PART%", part)
                    .replace("%OUTFIT_DISPLAYNAME%", ItemBuilder.getDisplayName(outfit)));
            itemBuilder.lore(CosmeticService.OUTFIT_EDITOR_EQUIPPED_DESCRIPTION_BUTTON
                    .stream().map(desc -> desc.replace("%OUTFIT_PART%", part))
                    .collect(Collectors.toList()));

            if (outfit.getItemMeta() instanceof LeatherArmorMeta) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) outfit.getItemMeta();
                itemBuilder.armorColor(leatherArmorMeta.getColor());
            }
            return itemBuilder.build();
        }
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (clickType.isLeftClick()) {
            OutfitChestplateMenu outfitChestplateMenu = new OutfitChestplateMenu();
            outfitChestplateMenu.openMenu(player);
        }
        if (clickType.isRightClick()) {
            if (player.getInventory().getChestplate() == null) return;
            player.getInventory().setChestplate(null);
            CompatibleSound.ITEM_BREAK.play(player);
        }
    }
}
