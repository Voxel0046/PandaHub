package dev.panda.hub.utilities;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@UtilityClass
public class PlayerUtil {

    public int getPing(Player player) {
        try {
            String a = Bukkit.getServer().getClass().getPackage().getName().substring(23);
            Class<?> b = Class.forName("org.bukkit.craftbukkit." + a + ".entity.CraftPlayer");
            Object c = b.getMethod("getHandle").invoke(player);
            return (int) c.getClass().getDeclaredField("ping").get(c);
        }
        catch (Exception ex) {
            return 0;
        }
    }

    public GameMode getGameMode(String text) {
        if (text.equalsIgnoreCase("0") || text.equalsIgnoreCase("survival")) {
            return GameMode.SURVIVAL;
        }
        else if (text.equalsIgnoreCase("1") || text.equalsIgnoreCase("creative")) {
            return GameMode.CREATIVE;
        }
        else if (text.equalsIgnoreCase("2") || text.equalsIgnoreCase("adventure")) {
            return GameMode.ADVENTURE;
        }
        else if (text.equalsIgnoreCase("3") || text.equalsIgnoreCase("spectator")) {
            return BukkitUtil.SERVER_VERSION_INT >= 8 ? GameMode.SPECTATOR : null;
        }
        else {
            return null;
        }
    }
}
