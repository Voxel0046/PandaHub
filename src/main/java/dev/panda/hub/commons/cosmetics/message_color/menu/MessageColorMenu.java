package dev.panda.hub.commons.cosmetics.message_color.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.CosmeticMenu;
import dev.panda.hub.commons.cosmetics.chat_color.menu.buttons.ChatColorRemoveButton;
import dev.panda.hub.commons.cosmetics.message_color.MessageColor;
import dev.panda.hub.commons.cosmetics.message_color.menu.buttons.MessageColorButton;
import dev.panda.hub.commons.cosmetics.message_color.menu.buttons.MessageColorRemoveButton;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class MessageColorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(CosmeticService.MESSAGE_COLOR_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(10, new MessageColorButton(MessageColor.DARK_RED));
        buttons.put(11, new MessageColorButton(MessageColor.RED));
        buttons.put(12, new MessageColorButton(MessageColor.ORANGE));
        buttons.put(13, new MessageColorButton(MessageColor.YELLOW));
        buttons.put(14, new MessageColorButton(MessageColor.DARK_GREEN));
        buttons.put(15, new MessageColorButton(MessageColor.GREEN));
        buttons.put(16, new MessageColorButton(MessageColor.DARK_BLUE));
        buttons.put(19, new MessageColorButton(MessageColor.BLUE));
        buttons.put(20, new MessageColorButton(MessageColor.DARK_AQUA));
        buttons.put(21, new MessageColorButton(MessageColor.AQUA));
        buttons.put(22, new MessageColorButton(MessageColor.PURPLE));
        buttons.put(23, new MessageColorButton(MessageColor.PINK));
        buttons.put(24, new MessageColorButton(MessageColor.DARK_GRAY));
        buttons.put(25, new MessageColorButton(MessageColor.BLACK));

        if (PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId()).getMessageColor() != null) {
            buttons.put(getSize() - 1, new MessageColorRemoveButton());
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
