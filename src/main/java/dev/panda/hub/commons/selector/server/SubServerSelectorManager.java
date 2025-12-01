package dev.panda.hub.commons.selector.server;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class SubServerSelectorManager {

    @Getter private final Map<String, SubServerSelector> subServers;
    private final FileConfig serverConfig;
    private final FileConfig subServerConfig;

    public SubServerSelectorManager() {
        this.subServers = Maps.newHashMap();
        this.serverConfig = PandaHub.get().getModuleService().getFileModule().getFile("server");
        this.subServerConfig = PandaHub.get().getModuleService().getFileModule().getFile("sub-server");
    }

    public void load() {
        subServers.clear();

        ConfigurationSection serverSection = serverConfig.getConfiguration().getConfigurationSection("SERVER_SELECTOR.SERVERS");

        if (serverSection == null) return;

        for (String server : serverSection.getKeys(false)) {
            boolean subServerEnabled = serverSection.getBoolean(server + ".SUB_SERVER");

            if (subServerEnabled) {
                ConfigurationSection subServerSection = subServerConfig.getConfiguration().getConfigurationSection("SUB_SERVER_SELECTOR.SERVERS." + server);

                if (subServerSection == null) return;

                for (String subServer : subServerSection.getKeys(false)) {
                    SubServerSelector subServerSelector = new SubServerSelector(subServer, server);
                    subServerSelector.setHead(subServerSection.getBoolean(subServer + ".ICON.HEAD.ENABLE"));

                    ItemStack itemStack;

                    if (subServerSelector.isHead()) {
                        if (!subServerSection.getString(subServer + ".ICON.HEAD.CUSTOM").isEmpty()) {
                            itemStack = PandaHub.get()
                                    .getModuleService()
                                    .getManagerModule()
                                    .getSkullManager()
                                    .getVersion()
                                    .createCustomSkull(
                                            subServerSection.getString(subServer + ".ICON.HEAD.CUSTOM"),
                                            subServerSection.getString(subServer + ".ICON.DISPLAYNAME"),
                                            subServerSection.getStringList(subServer + ".ICON.DESCRIPTION")
                            );
                        }
                        else if (!subServerSection.getString(subServer + ".ICON.HEAD.PLAYER").isEmpty()) {
                            itemStack = ItemBuilder.createSkull(
                                    subServerSection.getString(subServer + ".ICON.HEAD.PLAYER"),
                                    subServerSection.getString(subServer + ".ICON.DISPLAYNAME"),
                                    subServerSection.getStringList(subServer + ".ICON.DESCRIPTION")
                            );
                        }
                        else {
                            itemStack = new ItemBuilder(subServerSection.getString(subServer + ".ICON.MATERIAL"))
                                    .data(subServerSection.getInt(subServer + ".ICON.DATA"))
                                    .name(subServerSection.getString(subServer + ".ICON.DISPLAYNAME"))
                                    .lore(subServerSection.getStringList(subServer + ".ICON.DESCRIPTION"))
                                    .enchant(subServerSection.getBoolean(subServer + ".ICON.ENCHANTED"))
                                    .build();
                        }
                    }
                    else {
                        itemStack = new ItemBuilder(subServerSection.getString(subServer + ".ICON.MATERIAL"))
                                .data(subServerSection.getInt(subServer + ".ICON.DATA"))
                                .name(subServerSection.getString(subServer + ".ICON.DISPLAYNAME"))
                                .lore(subServerSection.getStringList(subServer + ".ICON.DESCRIPTION"))
                                .enchant(subServerSection.getBoolean(subServer + ".ICON.ENCHANTED"))
                                .build();
                    }

                    subServerSelector.setIcon(itemStack);
                    subServerSelector.setSlot(subServerSection.getInt(subServer + ".ICON.SLOT"));
                    subServerSelector.setPermission(subServerSection.getString(subServer + ".PERMISSION"));
                    subServerSelector.setSubServer(subServerSection.getString(subServer + ".SUB_SERVER"));
                    subServerSelector.setQueue(subServerSection.getBoolean(subServer + ".QUEUE"));
                    subServerSelector.setCommands(subServerSection.getStringList(subServer + ".COMMANDS"));
                    subServers.put(subServer, subServerSelector);
                }
            }
        }
    }
}
