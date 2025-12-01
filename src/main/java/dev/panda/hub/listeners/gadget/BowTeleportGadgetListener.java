package dev.panda.hub.listeners.gadget;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.utilities.TaskUtil;
import dev.panda.hub.utilities.compatibility.sound.CompatibleSound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class BowTeleportGadgetListener implements Listener {

    private ProfileManager profileManager;

    public BowTeleportGadgetListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onBowTeleport(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow && event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getGadget() != null && profile.getGadget().equals(Gadget.BOW_TELEPORT)) {
                player.teleport(event.getEntity().getLocation());
                event.getEntity().remove();
                CompatibleSound.EXPLODE.play(player);
            }
        }
    }

    @EventHandler
    private void onBowTeleportArrow(ProjectileLaunchEvent event) {
        if (event.getEntity().getShooter() instanceof Player) {
            if (event.getEntity() instanceof Arrow) {
                Player player = (Player) event.getEntity().getShooter();
                Profile profile = profileManager.getProfile(player.getUniqueId());

                if (profile.getGadget() != null && profile.getGadget().equals(Gadget.BOW_TELEPORT)) {
                    TaskUtil.runLater(() -> event.getEntity().remove(), 20L * 3L);
                }
            }
        }
    }
}
