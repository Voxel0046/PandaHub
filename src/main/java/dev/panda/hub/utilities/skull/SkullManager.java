package dev.panda.hub.utilities.skull;

import dev.panda.hub.utilities.BukkitUtil;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.skull.impl.Skull_v1_7;
import dev.panda.hub.utilities.skull.impl.Skull_v1_8;
import lombok.Getter;

@Getter
public class SkullManager {

    private SkullVersion version;

    public void load() {
        if (BukkitUtil.SERVER_VERSION_INT == 7) {
            this.version = new Skull_v1_7();
        }
        else if (BukkitUtil.SERVER_VERSION_INT >= 8) {
            this.version = new Skull_v1_8();
        }
        else {
            ChatUtil.log("&cSkull Version: Your server version is not supported!");
        }
    }
}
