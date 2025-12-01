package dev.panda.hub.commons.selector.server.menu.buttons;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.selector.server.ServerSelector;
import dev.panda.hub.commons.selector.server.menu.SubServerSelectorMenu;
import dev.panda.hub.utilities.ServerUtil;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class ServerSelectorButton extends Button {

    private final ServerSelector serverSelector;

    @Override
    public ItemStack getButtonItem(Player player) {
        return serverSelector.getCustomIcon(player).clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (serverSelector.isSubServer()) {
            new SubServerSelectorMenu(serverSelector.getName()).openMenu(player);
            return;
        }

        if (!serverSelector.getCommands().isEmpty()) {
            for (String command : serverSelector.getCommands()) {
                command = command.replace("%PLAYER%", player.getName());

                if (command.contains("[PLAYER]")) {
                    player.performCommand(command.replace("[PLAYER] ", ""));
                }
                else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }

        if (serverSelector.isQueue()) {
            PandaHub.get().getModuleService().getManagerModule().getQueueManager().getQueue().addToQueue(player, serverSelector.getServer());
        }
        else {
            if (!serverSelector.getServer().isEmpty()) {
                ServerUtil.sendBungeeServer(player, serverSelector.getServer());
            }
        }
    }
}
