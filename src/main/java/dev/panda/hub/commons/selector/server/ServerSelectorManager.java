package dev.panda.hub.commons.selector.server;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ServerSelectorManager {

    @Getter private final Map<String, ServerSelector> servers;
    private final FileConfig serverConfig;

    public ServerSelectorManager() {
        this.servers = Maps.newHashMap();
        this.serverConfig = PandaHub.get().getModuleService().getFileModule().getFile("server");
    }

    public void load() {
        servers.clear();

        ConfigurationSection section = serverConfig.getConfiguration().getConfigurationSection("SERVER_SELECTOR.SERVERS");

        if (section == null) return;

        for (String server : section.getKeys(false)) {
            ServerSelector serverSelector = new ServerSelector(server);
            serverSelector.setHead(section.getBoolean(server + ".ICON.HEAD.ENABLE"));

            ItemStack itemStack;

            if (serverSelector.isHead()) {
                if (!section.getString(server + ".ICON.HEAD.CUSTOM").isEmpty()) {
                    itemStack = PandaHub.get()
                            .getModuleService()
                            .getManagerModule()
                            .getSkullManager()
                            .getVersion()
                            .createCustomSkull(
                                    section.getString(server + ".ICON.HEAD.CUSTOM"),
                                    section.getString(server + ".ICON.DISPLAYNAME"),
                                    section.getStringList(server + ".ICON.DESCRIPTION")
                    );
                }
                else if (!section.getString(server + ".ICON.HEAD.PLAYER").isEmpty()) {
                    itemStack = ItemBuilder.createSkull(
                            section.getString(server + ".ICON.HEAD.PLAYER"),
                            section.getString(server + ".ICON.DISPLAYNAME"),
                            section.getStringList(server + ".ICON.DESCRIPTION")
                    );
                }
                else {
                    itemStack = new ItemBuilder(section.getString(server + ".ICON.MATERIAL"))
                            .data(section.getInt(server + ".ICON.DATA"))
                            .name(section.getString(server + ".ICON.DISPLAYNAME"))
                            .lore(section.getStringList(server + ".ICON.DESCRIPTION"))
                            .enchant(section.getBoolean(server + ".ICON.ENCHANTED"))
                            .build();
                }
            }
            else {
                itemStack = new ItemBuilder(section.getString(server + ".ICON.MATERIAL"))
                        .data(section.getInt(server + ".ICON.DATA"))
                        .name(section.getString(server + ".ICON.DISPLAYNAME"))
                        .lore(section.getStringList(server + ".ICON.DESCRIPTION"))
                        .enchant(section.getBoolean(server + ".ICON.ENCHANTED"))
                        .build();
            }

            serverSelector.setIcon(itemStack);
            serverSelector.setSlot(section.getInt(server + ".ICON.SLOT"));
            serverSelector.setServer(section.getString(server + ".SERVER"));
            serverSelector.setPermission(section.getString(server + ".PERMISSION"));
            serverSelector.setSubServer(section.getBoolean(server + ".SUB_SERVER"));
            serverSelector.setQueue(section.getBoolean(server + ".QUEUE"));
            serverSelector.setCommands(section.getStringList(server + ".COMMANDS"));
            servers.put(server, serverSelector);
        }
    }
}
