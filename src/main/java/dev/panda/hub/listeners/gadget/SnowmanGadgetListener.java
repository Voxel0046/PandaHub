package dev.panda.hub.listeners.gadget;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SnowmanGadgetListener implements Listener {

    private ProfileManager profileManager;

    public SnowmanGadgetListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onSnowman(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getGadget() != null && profile.getGadget().equals(Gadget.SNOWMAN)) {
                if (player.getItemInHand().isSimilar(Gadget.SNOWMAN.getGadgetItem())) {
                    event.setCancelled(true);

                    if (player.getVehicle() instanceof Player) {
                        ChatUtil.sendMessage(player, "&cYou cannot use this while on " + player.getVehicle().getName() + ".");
                        player.updateInventory();
                        return;
                    }

                    if (player.getVehicle() != null) {
                        player.getVehicle().remove();
                        player.eject();
                    }

                    Snowball snowball = player.launchProjectile(Snowball.class);

                    snowball.setVelocity(player.getLocation().getDirection());
                    snowball.setPassenger(player);
                    player.spigot().setCollidesWithEntities(false);
                    player.updateInventory();
                }
            }
        }
    }
}
