package dev.panda.hub.commons.selector.server.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.commons.selector.server.menu.buttons.ServerSelectorButton;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.selector.server.ServerSelector;
import dev.panda.hub.commons.selector.server.ServerSelectorManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.SelectorService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class ServerSelectorMenu extends Menu {

    private final ServerSelectorManager serverSelectorManager;

    public ServerSelectorMenu() {
        this.serverSelectorManager = PandaHub.get().getModuleService().getManagerModule().getServerSelectorManager();
    }

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(SelectorService.SERVER_SELECTOR_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * SelectorService.SERVER_SELECTOR_SIZE;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (ServerSelector server : serverSelectorManager.getServers().values()) {
            if (server.getPermission().isEmpty() || player.hasPermission(server.getPermission())) {
                buttons.put(server.getSlot(), new ServerSelectorButton(server));
            }
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
