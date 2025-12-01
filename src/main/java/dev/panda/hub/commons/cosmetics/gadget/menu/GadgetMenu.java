package dev.panda.hub.commons.cosmetics.gadget.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.CosmeticMenu;
import dev.panda.hub.commons.cosmetics.chat_color.menu.buttons.ChatColorRemoveButton;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.commons.cosmetics.gadget.menu.buttons.GadgetButton;
import dev.panda.hub.commons.cosmetics.gadget.menu.buttons.GadgetRemoveButton;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class GadgetMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(CosmeticService.GADGET_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * 3;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(10, new GadgetButton(Gadget.BOW_TELEPORT));
        buttons.put(11, new GadgetButton(Gadget.SNOWMAN));
        buttons.put(12, new GadgetButton(Gadget.RIDER));
        buttons.put(13, new GadgetButton(Gadget.FIREWORK));
        buttons.put(14, new GadgetButton(Gadget.GRAPPLING_HOOK));

        if (PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId()).getGadget() != null) {
            buttons.put(getSize() - 1, new GadgetRemoveButton());
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
