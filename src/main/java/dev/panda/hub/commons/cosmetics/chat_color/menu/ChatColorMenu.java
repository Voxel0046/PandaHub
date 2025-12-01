package dev.panda.hub.commons.cosmetics.chat_color.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.CosmeticMenu;
import dev.panda.hub.commons.cosmetics.chat_color.ChatColor;
import dev.panda.hub.commons.cosmetics.chat_color.menu.buttons.ChatColorButton;
import dev.panda.hub.commons.cosmetics.chat_color.menu.buttons.ChatColorRemoveButton;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class ChatColorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(CosmeticService.CHAT_COLOR_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(10, new ChatColorButton(ChatColor.DARK_RED));
        buttons.put(11, new ChatColorButton(ChatColor.RED));
        buttons.put(12, new ChatColorButton(ChatColor.ORANGE));
        buttons.put(13, new ChatColorButton(ChatColor.YELLOW));
        buttons.put(14, new ChatColorButton(ChatColor.DARK_GREEN));
        buttons.put(15, new ChatColorButton(ChatColor.GREEN));
        buttons.put(16, new ChatColorButton(ChatColor.DARK_BLUE));
        buttons.put(19, new ChatColorButton(ChatColor.BLUE));
        buttons.put(20, new ChatColorButton(ChatColor.DARK_AQUA));
        buttons.put(21, new ChatColorButton(ChatColor.AQUA));
        buttons.put(22, new ChatColorButton(ChatColor.PURPLE));
        buttons.put(23, new ChatColorButton(ChatColor.PINK));
        buttons.put(24, new ChatColorButton(ChatColor.DARK_GRAY));
        buttons.put(25, new ChatColorButton(ChatColor.BLACK));

        if (PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId()).getChatColor() != null) {
            buttons.put(getSize() - 1, new ChatColorRemoveButton());
        }

        return buttons;
    }

    @Override
    public Button getPlaceholderButton() {
        return new FillButton();
    }

    @Override
    public boolean isPlaceholder() {
        return ConfigService.MENU_FILL_BUTTON_ENABLE;
    }
}
