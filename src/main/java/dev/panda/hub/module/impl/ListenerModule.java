package dev.panda.hub.module.impl;

import dev.panda.hub.hooks.AJParkourHook;
import dev.panda.hub.listeners.*;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.menu.ButtonListener;
import dev.panda.hub.PandaHub;
import dev.panda.hub.module.Module;
import dev.panda.hub.profile.ProfileListener;

public class ListenerModule extends Module {

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void onEnable(PandaHub plugin) {
        new ButtonListener(plugin);
        new PlayerListener(plugin);
        new ProfileListener(plugin);
        new ChatListener(plugin);
        new HotbarListener(plugin);
        new CustomHotbarListener(plugin);
        new TrailListener(plugin);
        new GadgetListener(plugin);
        new BorderListener(plugin, ConfigService.DISABLE_WORLD_BORDER);
        new WorldListener(plugin);
        new PvPModeListener(plugin);
        new SkullListener(plugin);
        new OutfitListener(plugin);
        new CommunicationListener(plugin);

        if (AJParkourHook.isAjParkour()) new ParkourFixListener(plugin);
    }
}
