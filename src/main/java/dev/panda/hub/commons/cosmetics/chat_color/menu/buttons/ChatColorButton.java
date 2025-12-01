package dev.panda.hub.commons.cosmetics.chat_color.menu.buttons;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.chat_color.ChatColor;
import dev.panda.hub.commons.cosmetics.chat_color.menu.ChatColorMenu;
import dev.panda.hub.commons.cosmetics.message_color.menu.MessageColorMenu;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ChatColorButton extends Button {

    private ChatColor chatColor;
    private ProfileManager profileManager;

    public ChatColorButton(ChatColor chatColor) {
        this.chatColor = chatColor;
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
    }

    @Override
    public ItemStack getButtonItem(Player player) {
        return chatColor.getIcon(player, profileManager.getProfile(player.getUniqueId()));
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (player.hasPermission(chatColor.getPermission())) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getChatColor() != null && profile.getChatColor().equals(chatColor)) {
                playFail(player);
                return;
            }

            profile.setChatColor(chatColor);
            playSuccess(player);
            new ChatColorMenu().openMenu(player);
        }
        else if (player.hasPermission("pandahub.settings.chat_color.*")) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getChatColor() != null && profile.getChatColor().equals(chatColor)) {
                playFail(player);
                return;
            }

            profile.setChatColor(chatColor);
            playSuccess(player);
            new ChatColorMenu().openMenu(player);
        }
        else {
            playFail(player);
        }
    }
}
