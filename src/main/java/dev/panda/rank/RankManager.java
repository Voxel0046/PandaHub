package dev.panda.rank;

import org.bukkit.plugin.java.*;
import org.bukkit.*;
import dev.panda.rank.impl.*;
import java.util.*;

public class RankManager {

    private IRank rank;
    
    public RankManager(JavaPlugin plugin) {
        Collection<Class<?>> className = ClassUtil.getClassesInPackage(plugin, "dev.panda.rank.impl");
        boolean ANTONIOLOSER = false;
        for (Class<?> clazz : className) {
            String ranks = clazz.getSimpleName();
            if (Bukkit.getPluginManager().getPlugin(ranks) != null) {
                try {
                    this.rank = (IRank)clazz.newInstance();
                    ANTONIOLOSER = true;
                    break;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        if (!ANTONIOLOSER) {
            this.rank = new Default();
        }
    }
    
    public IRank getRank() {
        return this.rank;
    }
}
