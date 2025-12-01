package dev.panda.hub.commons.selector.server.menu.buttons;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.selector.server.SubServerSelector;
import dev.panda.hub.utilities.ServerUtil;
import dev.panda.hub.utilities.menu.Button;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
public class SubServerSelectorButton extends Button {

    private final SubServerSelector subServerSelector;

    @Override
    public ItemStack getButtonItem(Player player) {
        return subServerSelector.getCustomIcon(player).clone();
    }

    @Override
    public void clicked(Player player, int slot, ClickType clickType, int hotbarButton) {
        if (!subServerSelector.getCommands().isEmpty()) {
            for (String command : subServerSelector.getCommands()) {
                command = command.replace("%PLAYER%", player.getName());

                if (command.contains("[PLAYER]")) {
                    player.performCommand(command
                            .replace("[PLAYER] ", ""));
                }
                else {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                }
            }
        }

        if (subServerSelector.isQueue()) {
            PandaHub.get().getModuleService().getManagerModule().getQueueManager().getQueue().addToQueue(player, subServerSelector.getSubServer());
        }
        else {
            if (!subServerSelector.getSubServer().isEmpty()) {
                ServerUtil.sendBungeeServer(player, subServerSelector.getSubServer());
            }
        }
    }
}
