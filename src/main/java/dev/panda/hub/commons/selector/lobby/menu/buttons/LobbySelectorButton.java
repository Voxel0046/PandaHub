package dev.panda.hub.commons.selector.lobby.menu.buttons;

import dev.panda.hub.commons.selector.lobby.LobbySelector;
import dev.panda.hub.utilities.ServerUtil;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class LobbySelectorButton extends Button {

    private final LobbySelector lobbySelector;

    @Override
    public ItemStack getButtonItem(Player player) {
        return lobbySelector.getCustomIcon(player).clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        ServerUtil.sendBungeeServer(player, lobbySelector.getLobby());
    }
}
