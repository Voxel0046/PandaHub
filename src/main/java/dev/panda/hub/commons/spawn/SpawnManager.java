package dev.panda.hub.commons.spawn;

import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.PandaHub;
import dev.panda.hub.utilities.BukkitUtil;
import dev.panda.hub.utilities.file.FileConfig;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SpawnManager {

    private final FileConfig spawnConfig = PandaHub.get().getModuleService().getFileModule().getFile("spawn-data");;

    @Getter private Location location;

    public void load() {
        this.location = BukkitUtil.deserializeLocation(spawnConfig.getString("LOCATION"));
    }

    public void setLocation(Location location) {
        this.location = location;
        spawnConfig.getConfiguration().set("LOCATION", BukkitUtil.serializeLocation(location));
        spawnConfig.save();
    }

    public void toSpawn(Player player, boolean message) {
        if (location == null) {
            player.teleport(player.getWorld().getSpawnLocation());
            if (message) ChatUtil.sendMessage(player, LangService.SPAWN_NOT_SET);
        }
        else {
            player.teleport(location);
        }
    }
}
