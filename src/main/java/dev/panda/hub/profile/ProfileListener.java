package dev.panda.hub.profile;

import dev.panda.hub.services.impl.LangService;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ProfileListener implements Listener {

    private final PandaHub plugin;
    private final ProfileManager profileManager;

    public ProfileListener(PandaHub plugin) {
        this.plugin = plugin;
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent event) {
        Profile profile = profileManager.createProfile(event.getUniqueId(), event.getName());
        profile.load();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        Profile profile = profileManager.getProfile(event.getPlayer().getUniqueId());

        if (profile == null) {
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(ChatUtil.translate("&cFailed in load your profile, please join again."));
        }
    }

    @EventHandler
    private void onPlayerSaveProfile(PlayerQuitEvent event) {
        Profile profile = profileManager.getProfile(event.getPlayer().getUniqueId());

        if (profile != null) {
            if (profile.isPvpMode()) {
                profile.setPvpMode(false);
            }
            if (profile.isBuildMode()) {
                profile.setBuildMode(false);
            }
            profile.save(true, true);
        }
    }
}
