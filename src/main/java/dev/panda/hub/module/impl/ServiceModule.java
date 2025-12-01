package dev.panda.hub.module.impl;

import dev.panda.hub.services.impl.*;
import dev.panda.hub.PandaHub;
import dev.panda.hub.module.Module;
import dev.panda.hub.services.Service;

public class ServiceModule extends Module {

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public void onEnable(PandaHub plugin) {
        new ConfigService();
        new CosmeticService();
        new HotbarService();
        new LangService();
        new QueueService();
        new ScoreboardService();
        new TablistService();
        new SelectorService();

        load(false);
    }

    public void load(boolean reload) {
        if (reload) PandaHub.get().getModuleService().getFileModule().reload();
        for (Service service : Service.getServices()) {
            service.initialize();
        }
    }
}
