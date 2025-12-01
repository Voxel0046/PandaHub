package dev.panda.hub.tasks;

import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AnnouncementTask implements Runnable {

    private FileConfig configFile;

    public AnnouncementTask() {
        this.configFile = PandaHub.get().getModuleService().getFileModule().getFile("config");
    }

    @Override
    public void run() {
        if (!Bukkit.getOnlinePlayers().isEmpty()) {
            ConfigurationSection section = configFile.getConfiguration().getConfigurationSection("ANNOUNCEMENT.MESSAGES");
            List<String> announces = new ArrayList<>(section.getKeys(false));

            configFile.getStringList("ANNOUNCEMENT.MESSAGES." + announces.get(ThreadLocalRandom.current().nextInt(announces.size())))
                    .forEach(announce -> Bukkit.broadcastMessage(ChatUtil.translate(announce)));
        }
    }
}
