package dev.panda.hub.module.impl;

import dev.panda.hub.commands.PandaHubCommand;
import dev.panda.hub.commands.PvPModeCommand;
import dev.panda.hub.commands.essentials.*;
import dev.panda.hub.commands.network.*;
import dev.panda.hub.commands.spawn.SetSpawnCommand;
import dev.panda.hub.commands.spawn.SpawnCommand;
import dev.panda.hub.commands.timer.TimerCommand;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.PandaHub;
import dev.panda.hub.module.Module;
import dev.panda.hub.commands.essentials.*;
import dev.panda.hub.commands.network.*;

public class CommandModule extends Module {

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public void onEnable(PandaHub plugin) {
        new PandaHubCommand();
        new BuildCommand();
        new TimerCommand();
        new DiscordCommand();
        new StoreCommand();
        new TeamspeakCommand();
        new TwitterCommand();
        new WebsiteCommand();
        new SpawnCommand();
        new SetSpawnCommand();
        new PvPModeCommand();

        if (ConfigService.CUSTOM_MESSAGE_ENABLE) {
            new MessageCommand();
            new ReplyCommand();
        }
    }
}
