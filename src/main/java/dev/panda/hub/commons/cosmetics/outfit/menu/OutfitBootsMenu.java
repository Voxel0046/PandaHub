package dev.panda.hub.commons.cosmetics.outfit.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.boots.OutfitBootsButton;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import dev.panda.hub.utilities.menu.pagination.PaginatedMenu;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.commons.cosmetics.outfit.OutfitManager;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutfitBootsMenu extends PaginatedMenu {

    private OutfitManager outfitManager;

    public OutfitBootsMenu() {
        this.outfitManager = PandaHub.get().getModuleService().getManagerModule().getOutfitManager();
    }

    @Override
    public String getPrePaginatedTitle(Player player) {
        return ChatUtil.translate(CosmeticService.OUTFIT_BOOTS_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * 4;
    }

    @Override
    public int getMaxItemsPerPage(Player player) {
        return 9 * 4;
    }

    @Override
    public Map<Integer, Button> getAllPagesButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        List<Integer> validSlots = Arrays.asList(
                10, 11, 12, 13, 14, 15, 16,
                19, 20, 21, 22, 23, 24,
                28, 29, 30, 31, 32, 33
        );

        int index = 0;

        for (Outfit outfit : outfitManager.getOutfits().values()) {
            if (outfit.getName().equalsIgnoreCase("rainbow")) continue;

            if (index >= validSlots.size()) break;

            buttons.put(validSlots.get(index), new OutfitBootsButton(outfit));
            index++;
        }

        return buttons;
    }


    @Override
    public Map<Integer, Button> getGlobalButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

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
