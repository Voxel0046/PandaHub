package dev.panda.hub.commons.cosmetics.outfit.menu.buttons;

import dev.panda.hub.commons.cosmetics.gadget.menu.GadgetMenu;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitEditorMenu;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitsMenu;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.commons.cosmetics.outfit.OutfitRainbow;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.LangService;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OutfitButton extends Button {

    private Outfit outfit;
    private ProfileManager profileManager;

    public OutfitButton(Outfit outfit) {
        this.outfit = outfit;
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return outfit.getChestplate().clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.hasPermission(outfit.getPermission())) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (outfit.getName().equalsIgnoreCase("rainbow")) {
                if (!profile.isOutfitRainbow()) {
                    OutfitRainbow armor = new OutfitRainbow(player, 0, 0, 255, 0, 0, 0, 0, 0, 0);
                    armor.runTaskTimerAsynchronously(PandaHub.get(), 0L, 1L);

                    profile.setOutfitRainbow(true);
                    profile.setOutfit(outfit);

                    playSuccess(player);
                    new OutfitEditorMenu().openMenu(player);
                    return;
                }

                playFail(player);
                return;
            }

            if (profile.isOutfitRainbow()) profile.setOutfitRainbow(false);

            profile.setOutfit(outfit);
            outfit.equipAll(player);
            playSuccess(player);
            new OutfitEditorMenu().openMenu(player);
        }
        else if (player.hasPermission("pandahub.cosmetics.outfit.*")) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (outfit.getName().equalsIgnoreCase("rainbow")) {
                if (!profile.isOutfitRainbow()) {
                    OutfitRainbow armor = new OutfitRainbow(player, 0, 0, 255, 0, 0, 0, 0, 0, 0);
                    armor.runTaskTimerAsynchronously(PandaHub.get(), 0L, 1L);

                    profile.setOutfitRainbow(true);
                    profile.setOutfit(outfit);

                    playSuccess(player);
                    new OutfitEditorMenu().openMenu(player);
                    return;
                }

                playFail(player);
                return;
            }

            if (profile.isOutfitRainbow()) profile.setOutfitRainbow(false);

            profile.setOutfit(outfit);
            outfit.equipAll(player);
            playSuccess(player);
            new OutfitEditorMenu().openMenu(player);
        }
        else {
            playFail(player);
        }
    }
}
