package dev.panda.hub.commons.selector.lobby.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.commons.selector.lobby.menu.buttons.LobbySelectorButton;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.selector.lobby.LobbySelector;
import dev.panda.hub.commons.selector.lobby.LobbySelectorManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.SelectorService;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class LobbySelectorMenu extends Menu {

    private final LobbySelectorManager lobbySelectorManager;

    public LobbySelectorMenu() {
        this.lobbySelectorManager = PandaHub.get().getModuleService().getManagerModule().getLobbySelectorManager();
    }

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(SelectorService.LOBBY_SELECTOR_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * SelectorService.LOBBY_SELECTOR_SIZE;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (LobbySelector lobby : lobbySelectorManager.getLobbies().values()) {
            buttons.put(lobby.getSlot(), new LobbySelectorButton(lobby));
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
