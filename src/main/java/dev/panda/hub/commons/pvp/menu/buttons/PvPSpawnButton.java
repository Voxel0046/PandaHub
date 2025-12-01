package dev.panda.hub.commons.pvp.menu.buttons;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.item.ItemBuilder;
import dev.panda.hub.utilities.menu.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class PvPSpawnButton extends Button {

    @Override
    public ItemStack getButtonItem(Player player) {
        return new ItemBuilder(Material.GRASS)
                .name("&9&lPvP Location")
                .lore("&7Click here to set spawn.")
                .build();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        playSuccess(player);

        PandaHub.get().getModuleService().getManagerModule().getPvpManager().setLocation(player.getLocation());
        ChatUtil.sendMessage(player, LangService.PVP_LOCATION_SET
                .replace("%WORLD%", player.getWorld().getName())
                .replace("%X%", String.valueOf(Math.round(player.getLocation().getX())))
                .replace("%Y%", String.valueOf(Math.round(player.getLocation().getY())))
                .replace("%Z%", String.valueOf(Math.round(player.getLocation().getZ()))));
    }
}
