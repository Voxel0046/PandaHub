package dev.panda.hub.utilities.compatibility.sound;

import dev.panda.hub.utilities.ChatUtil;
import lombok.experimental.UtilityClass;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@UtilityClass
public class SoundUtil {

    public void play(Player player, String sound) {
        if (!sound.isEmpty()) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1F, 1F);
            }
            catch (Exception ex) {
                ChatUtil.log("&cSound '" + sound + "' is not valid, please use a valid sound.");
            }
        }
    }
}
