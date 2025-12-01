package dev.panda.hub.module.impl;

import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.PandaHub;
import dev.panda.hub.module.Module;
import dev.panda.hub.tasks.AnnouncementTask;
import dev.panda.hub.utilities.TaskUtil;

public class TaskModule extends Module {

    @Override
    public int getPriority() {
        return 6;
    }

    @Override
    public void onEnable(PandaHub plugin) {
        if (ConfigService.ANNOUNCEMENT_ENABLE) {
            int minutes = ConfigService.ANNOUNCEMENT_INTERVAL * 60;
            TaskUtil.runTaskTimerAsynchronously(new AnnouncementTask(), minutes, minutes);
        }
    }
}
