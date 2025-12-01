package dev.panda.hub.commons.pvp;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.BukkitUtil;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.file.FileConfig;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PvPManager {

    private final FileConfig pvpModeConfig = PandaHub.get().getModuleService().getFileModule().getFile("pvpmode-data");

    @Getter private Location location;
    @Getter private ItemStack[] armor;
    @Getter private ItemStack[] contents;

    public void load() {
        this.location = BukkitUtil.deserializeLocation(pvpModeConfig.getString("LOCATION"));
        this.armor = BukkitUtil.deserializeItemStackArray(pvpModeConfig.getString("LOOT.ARMOR"));
        this.contents = BukkitUtil.deserializeItemStackArray(pvpModeConfig.getString("LOOT.CONTENTS"));
    }

    public void setLocation(Location location) {
        this.location = location;

        pvpModeConfig.getConfiguration().set("LOCATION", BukkitUtil.serializeLocation(location));
        pvpModeConfig.save();
    }

    public void setLoot(ItemStack[] armor, ItemStack[] contents) {
        this.armor = armor;
        this.contents = contents;

        pvpModeConfig.getConfiguration().set("LOOT.ARMOR", BukkitUtil.serializeItemStackArray(armor));
        pvpModeConfig.getConfiguration().set("LOOT.CONTENTS", BukkitUtil.serializeItemStackArray(contents));
        pvpModeConfig.save();
    }

    public void toEquip(Player player) {
        player.getInventory().setContents(contents);
        player.getInventory().setArmorContents(armor);

        Hotbar pvpLeave = PandaHub.get().getModuleService().getManagerModule().getHotbarManager().getHotbar("PVP_MODE_LEAVE");

        if (pvpLeave.isEnable()) {
            player.getInventory().setItem(pvpLeave.getSlot() - 1, pvpLeave.getItem());
        }

        player.updateInventory();
    }

    public void toSpawn(Player player, boolean message) {
        if (location == null) {
            player.teleport(player.getWorld().getSpawnLocation());
            if (message) ChatUtil.sendMessage(player, LangService.PVP_LOCATION_NOT_SET);
        }
        else {
            player.teleport(location);
            if (message) ChatUtil.sendMessage(player, LangService.PVP_LOCATION_SPAWNED);
        }
    }
}
