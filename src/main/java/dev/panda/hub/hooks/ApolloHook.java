package dev.panda.hub.hooks;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class ApolloHook {

    @Getter @Setter
    private boolean apollo;

    public void init() {
        if (Bukkit.getPluginManager().getPlugin("Apollo-Bukkit") != null) {
            setApollo(true);
        }
    }
}
