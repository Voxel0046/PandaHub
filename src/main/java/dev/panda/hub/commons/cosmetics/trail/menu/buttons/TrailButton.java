package dev.panda.hub.commons.cosmetics.trail.menu.buttons;

import dev.panda.hub.commons.cosmetics.trail.Trail;
import dev.panda.hub.commons.cosmetics.trail.menu.TrailMenu;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.PandaHub;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class TrailButton extends Button {

    private Trail trail;
    private ProfileManager profileManager;

    public TrailButton(Trail trail) {
        this.trail = trail;
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return trail.getIcon(player, profileManager.getProfile(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.hasPermission(trail.getPermission())) {
            Profile profile = profileManager.getProfile(player.getUniqueId());
            
            if (profile.getTrail() != null && profile.getTrail().equals(trail)) {
                playFail(player);
                return;
            }
            
            profile.setTrail(trail);
            playSuccess(player);
            new TrailMenu().openMenu(player);
        }
        else if (player.hasPermission("pandahub.cosmetics.trail.*")) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getTrail() != null && profile.getTrail().equals(trail)) {
                playFail(player);
                return;
            }

            profile.setTrail(trail);
            playSuccess(player);
            new TrailMenu().openMenu(player);
        }
        else {
            playFail(player);
        }
    }
}
