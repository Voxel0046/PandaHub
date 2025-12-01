package dev.panda.hub.hooks;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class PlaceholderAPIHook {

    @Getter @Setter
    private boolean placeholderAPI;

    public void init() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            PlaceholderAPIExpansion papi = new PlaceholderAPIExpansion();

            if (!papi.isRegistered()) papi.register();

            setPlaceholderAPI(true);
        }
    }
}
