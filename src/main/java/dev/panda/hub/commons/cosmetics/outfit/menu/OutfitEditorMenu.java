package dev.panda.hub.commons.cosmetics.outfit.menu;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.CosmeticMenu;
import dev.panda.hub.commons.cosmetics.chat_color.menu.buttons.ChatColorRemoveButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.OutfitRemoveButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.OutfitsButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.boots.OutfitEquipBootsButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.chestplate.OutfitEquipChestplateButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.helmet.OutfitEquipHelmetButton;
import dev.panda.hub.commons.cosmetics.outfit.menu.buttons.leggings.OutfitEquipLeggingsButton;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.menu.Button;
import dev.panda.hub.utilities.menu.Menu;
import dev.panda.hub.utilities.menu.buttons.BackButton;
import dev.panda.hub.utilities.menu.buttons.FillButton;
import org.bukkit.entity.Player;

import java.util.Map;

public class OutfitEditorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return ChatUtil.translate(CosmeticService.OUTFIT_EDITOR_TITLE);
    }

    @Override
    public int getSize() {
        return 9 * 3;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(10, new OutfitEquipHelmetButton("Helmet"));
        buttons.put(11, new OutfitEquipChestplateButton("Chestplate"));
        buttons.put(12, new OutfitEquipLeggingsButton("Leggings"));
        buttons.put(13, new OutfitEquipBootsButton("Boots"));
        buttons.put(16, new OutfitsButton());

        if (PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId()).getOutfit() != null) {
            buttons.put(15, new OutfitRemoveButton());
        }

        return buttons;
    }

    @Override
    public boolean isUpdateAfterClick() {
        return true;
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
