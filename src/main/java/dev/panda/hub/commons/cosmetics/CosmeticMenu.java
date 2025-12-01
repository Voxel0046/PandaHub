package dev.panda.hub.commons.cosmetics;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class CosmeticMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(CosmeticService.COSMETIC_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * CosmeticService.COSMETIC_SIZE;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        for (Cosmetic cosmetic : PandaHub.get().getModuleService().getManagerModule().getCosmeticManager().getCosmetics().values()) {
            if (cosmetic.isEnable()) buttons.put(cosmetic.getSlot(), new CosmeticButton(cosmetic));
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
