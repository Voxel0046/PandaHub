package dev.panda.hub.listeners.gadget;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class RiderGadgetListener implements Listener {

    private ProfileManager profileManager;

    public RiderGadgetListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onRider(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().isSimilar(Gadget.RIDER.getGadgetItem())) {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile == null) return;

            if (profile.getGadget() != null && profile.getGadget().equals(Gadget.RIDER)) {
                event.setCancelled(true);

                if (event.getRightClicked() instanceof Player) {
                    if (player.getVehicle() instanceof Player) {
                        ChatUtil.sendMessage(player, "&cYou cannot use this while on " + player.getVehicle().getName() + ".");
                        return;
                    }

                    Player target = (Player) event.getRightClicked();
                    Profile targetProfile = profileManager.getProfile(target.getUniqueId());

                    if (targetProfile == null) return;

                    if (targetProfile.isPvpMode()) {
                        ChatUtil.sendMessage(player, "&cYou cannot use this while on " + target.getName() + ".");
                        return;
                    }

                    if (player.getVehicle() != null) {
                        player.getVehicle().remove();
                        player.eject();
                    }

                    target.setPassenger(player);
                    player.spigot().setCollidesWithEntities(false);
                    player.updateInventory();
                }
            }
        }
    }
}
