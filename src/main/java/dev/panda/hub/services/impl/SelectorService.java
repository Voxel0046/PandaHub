package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;

public class SelectorService extends Service {

    private final FileConfig serverSelectorConfig = PandaHub.get().getModuleService().getFileModule().getFile("server");
    private final FileConfig subServerSelectorConfig = PandaHub.get().getModuleService().getFileModule().getFile("sub-server");
    private final FileConfig lobbySelectorConfig = PandaHub.get().getModuleService().getFileModule().getFile("lobby");

    public static String SERVER_SELECTOR_TITLE;
    public static int SERVER_SELECTOR_SIZE;

    public static String SUB_SERVER_SELECTOR_TITLE;
    public static int SUB_SERVER_SELECTOR_SIZE;

    public static String LOBBY_SELECTOR_TITLE;
    public static int LOBBY_SELECTOR_SIZE;

    @Override
    public void initialize() {
        SERVER_SELECTOR_TITLE = serverSelectorConfig.getString("SERVER_SELECTOR.TITLE");
        SERVER_SELECTOR_SIZE = serverSelectorConfig.getInt("SERVER_SELECTOR.SIZE");

        SUB_SERVER_SELECTOR_TITLE = subServerSelectorConfig.getString("SUB_SERVER_SELECTOR.TITLE");
        SUB_SERVER_SELECTOR_SIZE = subServerSelectorConfig.getInt("SUB_SERVER_SELECTOR.SIZE");

        LOBBY_SELECTOR_TITLE = lobbySelectorConfig.getString("LOBBY_SELECTOR.TITLE");
        LOBBY_SELECTOR_SIZE = lobbySelectorConfig.getInt("LOBBY_SELECTOR.SIZE");
    }
}
