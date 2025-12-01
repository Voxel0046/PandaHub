package dev.panda.hub.commons.selector.lobby;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class LobbySelectorManager {

    @Getter private final Map<String, LobbySelector> lobbies;
    private final FileConfig lobbyConfig;

    public LobbySelectorManager() {
        this.lobbies = Maps.newHashMap();
        this.lobbyConfig = PandaHub.get().getModuleService().getFileModule().getFile("lobby");
    }

    public void load() {
        lobbies.clear();

        ConfigurationSection section = lobbyConfig.getConfiguration().getConfigurationSection("LOBBY_SELECTOR.LOBBYS");

        if (section == null) return;

        for (String lobby : section.getKeys(false)) {
            LobbySelector lobbySelector = new LobbySelector(lobby);
            lobbySelector.setHead(section.getBoolean(lobby + ".ICON.HEAD.ENABLE"));

            ItemStack itemStack;

            if (lobbySelector.isHead()) {
                if (!section.getString(lobby + ".ICON.HEAD.CUSTOM").isEmpty()) {
                    itemStack = PandaHub.get()
                            .getModuleService()
                            .getManagerModule()
                            .getSkullManager()
                            .getVersion()
                            .createCustomSkull(
                                    section.getString(lobby + ".ICON.HEAD.CUSTOM"),
                                    section.getString(lobby + ".ICON.DISPLAYNAME"),
                                    section.getStringList(lobby + ".ICON.DESCRIPTION")
                    );
                }
                else if (!section.getString(lobby + ".ICON.HEAD.PLAYER").isEmpty()) {
                    itemStack = ItemBuilder.createSkull(
                            section.getString(lobby + ".ICON.HEAD.PLAYER"),
                            section.getString(lobby + ".ICON.DISPLAYNAME"),
                            section.getStringList(lobby + ".ICON.DESCRIPTION")
                    );
                }
                else {
                    itemStack = new ItemBuilder(section.getString(lobby + ".ICON.MATERIAL"))
                            .data(section.getInt(lobby + ".ICON.DATA"))
                            .name(section.getString(lobby + ".ICON.DISPLAYNAME"))
                            .lore(section.getStringList(lobby + ".ICON.DESCRIPTION"))
                            .enchant(section.getBoolean(lobby + ".ICON.ENCHANTED"))
                            .build();
                }
            }
            else {
                itemStack = new ItemBuilder(section.getString(lobby + ".ICON.MATERIAL"))
                        .data(section.getInt(lobby + ".ICON.DATA"))
                        .name(section.getString(lobby + ".ICON.DISPLAYNAME"))
                        .lore(section.getStringList(lobby + ".ICON.DESCRIPTION"))
                        .enchant(section.getBoolean(lobby + ".ICON.ENCHANTED"))
                        .build();
            }

            lobbySelector.setIcon(itemStack);
            lobbySelector.setSlot(section.getInt(lobby + ".ICON.SLOT"));
            lobbySelector.setLobby(section.getString(lobby + ".LOBBY"));
            lobbies.put(lobby, lobbySelector);
        }
    }
}
