package dev.panda.hub.services.impl;

import dev.panda.hub.PandaHub;
import dev.panda.hub.services.Service;
import dev.panda.hub.utilities.file.FileConfig;
import java.util.List;

public class HotbarService extends Service {

    private final FileConfig hotbarConfig = PandaHub.get().getModuleService().getFileModule().getFile("hotbar");

    public static String GADGET_NAME;
    public static int GADGET_SLOT;
    public static List<String> INFORMATION;

    @Override
    public void initialize() {
        GADGET_NAME = hotbarConfig.getString("GADGET.DISPLAYNAME");
        GADGET_SLOT = hotbarConfig.getInt("GADGET.SLOT");
        INFORMATION = hotbarConfig.getStringList("INFORMATION.INFO");
    }
}
