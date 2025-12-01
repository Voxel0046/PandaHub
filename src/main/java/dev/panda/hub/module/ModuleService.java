package dev.panda.hub.module;

import dev.panda.hub.module.impl.*;
import dev.panda.hub.providers.scoreboard.ScoreboardHook;
import dev.panda.hub.PandaHub;
import dev.panda.hub.hooks.PlaceholderAPIHook;
import dev.panda.hub.providers.tab.TablistHook;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.Comparator;
import java.util.List;

public class ModuleService {

    @Getter private final FileModule fileModule;
    @Getter private final ServiceModule serviceModule;
    @Getter private final ManagerModule managerModule;
    @Getter private final ListenerModule listenerModule;
    @Getter private final CommandModule commandModule;
    @Getter private final TaskModule taskModule;

    public ModuleService() {
        fileModule = new FileModule();
        serviceModule = new ServiceModule();
        managerModule = new ManagerModule();
        listenerModule = new ListenerModule();
        commandModule = new CommandModule();
        taskModule = new TaskModule();
    }

    public void start(PandaHub plugin) {
        for (Module module : getModules()) {
            module.onEnable(plugin);
        }

        PlaceholderAPIHook.init();
        ScoreboardHook.init(plugin);
        TablistHook.init(plugin);

        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }

    public void stop() {
        managerModule.getProfileManager().save();
    }

    public List<Module> getModules() {
        Module.getModules().sort(Comparator.comparingInt(Module::getPriority));
        return Module.getModules();
    }
}
