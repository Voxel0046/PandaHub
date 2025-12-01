package dev.panda.hub.commons.cosmetics.message_color.menu.buttons;

import dev.panda.hub.commons.cosmetics.message_color.MessageColor;
import dev.panda.hub.commons.cosmetics.message_color.menu.MessageColorMenu;
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

public class MessageColorButton extends Button {

    private MessageColor messageColor;
    private ProfileManager profileManager;

    public MessageColorButton(MessageColor messageColor) {
        this.messageColor = messageColor;
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return messageColor.getIcon(player, profileManager.getProfile(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.hasPermission(messageColor.getPermission())) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getMessageColor() != null && profile.getMessageColor().equals(messageColor)) {
                playNeutral(player);
                return;
            }

            profile.setMessageColor(messageColor);
            playSuccess(player);
            new MessageColorMenu().openMenu(player);
        }
        else if (player.hasPermission("pandahub.settings.message_color.*")) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getMessageColor() != null && profile.getMessageColor().equals(messageColor)) {
                playNeutral(player);
                return;
            }

            profile.setMessageColor(messageColor);
            playSuccess(player);
            new MessageColorMenu().openMenu(player);
        }
        else {
            playFail(player);
        }
    }
}
