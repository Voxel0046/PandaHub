package dev.panda.hub.commons.cosmetics.gadget.menu.buttons;

import dev.panda.hub.commons.cosmetics.chat_color.menu.ChatColorMenu;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.commons.cosmetics.gadget.menu.GadgetMenu;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.PandaHub;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class GadgetButton extends Button {

    private final Gadget gadget;;
    private final ProfileManager profileManager;

    public GadgetButton(Gadget gadget) {
        this.gadget = gadget;
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return gadget.getIcon(player, profileManager.getProfile(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.hasPermission(gadget.getPermission())) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getGadget() != null && profile.getGadget().equals(gadget)) {
                playNeutral(player);
                return;
            }

            profile.setGadget(gadget);
            playSuccess(player);
            new GadgetMenu().openMenu(player);
            gadget.giveGadgetItem(player);
        }
        else if (player.hasPermission("pandahub.cosmetics.gadget.*")) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getGadget() != null && profile.getGadget().equals(gadget)) {
                playNeutral(player);
                return;
            }

            profile.setGadget(gadget);
            playSuccess(player);
            new GadgetMenu().openMenu(player);
            gadget.giveGadgetItem(player);
        }
        else {
            playFail(player);
        }
    }
}
