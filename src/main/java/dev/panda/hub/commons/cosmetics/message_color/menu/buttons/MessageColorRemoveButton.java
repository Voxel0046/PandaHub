package dev.panda.hub.commons.cosmetics.message_color.menu.buttons;

import dev.panda.hub.commons.cosmetics.chat_color.menu.ChatColorMenu;
import dev.panda.hub.commons.cosmetics.message_color.menu.MessageColorMenu;
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

public class MessageColorRemoveButton extends Button {

    private ProfileManager profileManager;

    public MessageColorRemoveButton() {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return CosmeticService.MESSAGE_COLOR_REMOVE_BUTTON;
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (profile.getMessageColor() == null) {
            playFail(player);
            return;
        }

        profile.setMessageColor(null);
        playSuccess(player);
        new MessageColorMenu().openMenu(player);
    }
}
