package dev.panda.hub.hooks;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class AJParkourHook {

    @Getter @Setter
    private boolean ajParkour;

    public void init() {
        if (Bukkit.getPluginManager().getPlugin("ajParkour") != null) {
            setAjParkour(true);
        }
    }
}
