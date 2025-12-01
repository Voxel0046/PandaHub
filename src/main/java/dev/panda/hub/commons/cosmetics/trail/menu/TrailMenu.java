package dev.panda.hub.commons.cosmetics.trail.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.CosmeticMenu;
import dev.panda.hub.commons.cosmetics.chat_color.menu.buttons.ChatColorRemoveButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.OutfitRemoveButton;
import dev.panda.hub.commons.cosmetics.trail.Trail;
import dev.panda.hub.commons.cosmetics.trail.menu.buttons.TrailButton;
import dev.panda.hub.commons.cosmetics.trail.menu.buttons.TrailRemoveButton;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class TrailMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(CosmeticService.TRAIL_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(10, new TrailButton(Trail.HEART));
        buttons.put(11, new TrailButton(Trail.FLAME));
        buttons.put(12, new TrailButton(Trail.SLIME));
        buttons.put(13, new TrailButton(Trail.NOTE));
        buttons.put(14, new TrailButton(Trail.CLOUD));
        buttons.put(15, new TrailButton(Trail.SMOKE));
        buttons.put(16, new TrailButton(Trail.VILLAGER));
        buttons.put(19, new TrailButton(Trail.CRITICAL));
        buttons.put(20, new TrailButton(Trail.EXPLOSION));
        buttons.put(21, new TrailButton(Trail.FIREWORK));
        buttons.put(22, new TrailButton(Trail.LAVA));
        buttons.put(23, new TrailButton(Trail.WATER));
        buttons.put(24, new TrailButton(Trail.SNOW));
        buttons.put(25, new TrailButton(Trail.SPELL));

        if (PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId()).getTrail() != null) {
            buttons.put(getSize() - 1, new TrailRemoveButton());
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
