package dev.panda.hub.commons.pvp.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.commons.pvp.menu.buttons.PvPLootButton;
import dev.panda.hub.commons.pvp.menu.buttons.PvPSpawnButton;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class PvPMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "PvP Mode Options";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(3, new PvPSpawnButton());
        buttons.put(5, new PvPLootButton());

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
