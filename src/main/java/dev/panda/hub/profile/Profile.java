package dev.panda.hub.profile;

import dev.panda.hub.commons.cosmetics.chat_color.ChatColor;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.commons.cosmetics.message_color.MessageColor;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.commons.cosmetics.trail.Trail;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class Profile {

    @Getter
    private IProfile profile;

    private UUID uuid;
    private String name;

    private int kills;
    private int streak;
    private int deaths;
    private boolean pvpMode;
    private boolean buildMode;
    private boolean flyMode;
    private Trail trail;
    private Gadget gadget;
    private MessageColor messageColor;
    private ChatColor chatColor;
    private Outfit outfit;
    private boolean outfitRainbow;

    public Profile(IProfile profile, UUID uuid, String name) {
        this.profile = profile;
        this.uuid = uuid;
        this.name = name;
    }

    public String getTranslateMessageColor() {
        return messageColor == null ? "&7" : messageColor.getColor();
    }

    public String getTranslateChatColor() {
        return messageColor == null ? "&r" : messageColor.getColor();
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public void save(boolean destroy, boolean async) {
        profile.save(this, destroy, async);
    }

    public void load() {
        profile.load(this);
    }
}
