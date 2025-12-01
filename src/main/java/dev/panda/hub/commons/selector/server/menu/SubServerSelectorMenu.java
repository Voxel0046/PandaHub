package dev.panda.hub.commons.selector.server.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.commons.selector.server.menu.buttons.SubServerSelectorButton;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.selector.server.SubServerSelector;
import dev.panda.hub.commons.selector.server.SubServerSelectorManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.SelectorService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class SubServerSelectorMenu extends Menu {

    private final String server;
    private final SubServerSelectorManager subServerSelectorManager;

    public SubServerSelectorMenu(String server) {
        this.server = server;
        this.subServerSelectorManager = PandaHub.get().getModuleService().getManagerModule().getSubServerSelectorManager();
    }


    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(SelectorService.SUB_SERVER_SELECTOR_TITLE
                .replace("%SERVER%", server));
    }

    @Override
    public int getSize() {
        return 9 * SelectorService.SUB_SERVER_SELECTOR_SIZE;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (SubServerSelector subServer : subServerSelectorManager.getSubServers().values()) {
            if (subServer.getServer().equalsIgnoreCase(server)) {
                if (subServer.getPermission().isEmpty() || player.hasPermission(subServer.getPermission())) {
                    buttons.put(subServer.getSlot(), new SubServerSelectorButton(subServer));
                }
            }
        }

        if (ConfigService.MENU_BACK_BUTTON_ENABLE) {
            buttons.put(getSize()-1, new BackButton(new ServerSelectorMenu()));
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
