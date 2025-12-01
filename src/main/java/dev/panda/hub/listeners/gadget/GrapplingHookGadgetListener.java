package dev.panda.hub.listeners.gadget;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class GrapplingHookGadgetListener implements Listener {

    private ProfileManager profileManager;

    public GrapplingHookGadgetListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onGrapplingHook(PlayerFishEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (profile.getGadget() != null && profile.getGadget().equals(Gadget.GRAPPLING_HOOK)) {
            if (player.getItemInHand().isSimilar(Gadget.GRAPPLING_HOOK.getGadgetItem())) {
                if (event.getState().equals(PlayerFishEvent.State.FAILED_ATTEMPT)) {
                    player.setVelocity(player.getLocation().getDirection().normalize().setY(5F));
                    player.setVelocity(player.getLocation().getDirection().normalize().multiply(3F));
                }
            }
        }
    }
}
