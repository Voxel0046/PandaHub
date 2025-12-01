package dev.panda.hub.utilities.compatibility.effect;

import dev.panda.hub.utilities.ChatUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

@UtilityClass
public class EffectUtil {

    public void play(Player player, String effect) {
        if (!effect.isEmpty()) {
            try {
                player.getWorld().playEffect(player.getLocation(), Effect.valueOf(effect), 1);
            }
            catch (Exception ex) {
                ChatUtil.log("&cEffect '" + effect + "' is not valid, please use a valid effect.");
            }
        }
    }
}
