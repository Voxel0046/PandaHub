package dev.panda.hub.commons.pvp.menu.buttons;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.pvp.PvPManager;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.item.ItemBuilder;
import dev.panda.hub.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PvPLootButton extends Button {

    private PvPManager pvpManager;

    public PvPLootButton() {
        this.pvpManager = PandaHub.get().getModuleService().getManagerModule().getPvpManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.CHEST)
                .name("&9&lPvP Kit")
                .lore("&7Click here to set the PvP kit.")
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        ItemStack[] armor = new ItemStack[4];
        ItemStack[] contents = new ItemStack[36];

        for (int i = 0; i < 4; i++) {
            if (player.getInventory().getArmorContents()[i] == null) continue;
            armor[i] = player.getInventory().getArmorContents()[i].clone();
        }
        for (int i = 0; i < 36; i++) {
            if (player.getInventory().getContents()[i] == null) continue;
            contents[i] = player.getInventory().getContents()[i].clone();
        }

        pvpManager.setLoot(armor, contents);

        playSuccess(player);
        ChatUtil.sendMessage(player, LangService.PVP_LOOT);
    }
}
