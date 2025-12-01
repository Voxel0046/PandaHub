package dev.panda.hub.commons.cosmetics.trail.menu.buttons;

import dev.panda.hub.commons.cosmetics.chat_color.menu.ChatColorMenu;
import dev.panda.hub.commons.cosmetics.trail.menu.TrailMenu;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.PandaHub;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class TrailRemoveButton extends Button {

    private ProfileManager profileManager;

    public TrailRemoveButton() {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return CosmeticService.TRAIL_REMOVE_BUTTON;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (profile.getTrail() == null) {
            playFail(player);
            return;
        }

        profile.setTrail(null);
        playSuccess(player);
        new TrailMenu().openMenu(player);
    }
}
