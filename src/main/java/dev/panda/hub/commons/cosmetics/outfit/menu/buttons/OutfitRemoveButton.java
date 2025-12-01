package dev.panda.hub.commons.cosmetics.outfit.menu.buttons;

import dev.panda.hub.commons.cosmetics.chat_color.menu.ChatColorMenu;
import dev.panda.hub.commons.cosmetics.outfit.menu.OutfitEditorMenu;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class OutfitRemoveButton extends Button {

    private ProfileManager profileManager;

    public OutfitRemoveButton() {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return CosmeticService.OUTFIT_EDITOR_REMOVE_BUTTON;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (profile.getOutfit() == null) {
            playFail(player);
            return;
        }

        if (profile.isOutfitRainbow()) profile.setOutfitRainbow(false);

        profile.getOutfit().unEquipAll(player);
        profile.setOutfit(null);
        playSuccess(player);
        new OutfitEditorMenu().openMenu(player);
    }
}
