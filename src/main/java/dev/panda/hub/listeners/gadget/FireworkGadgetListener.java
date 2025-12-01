package dev.panda.hub.listeners.gadget;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkGadgetListener implements Listener {

    private ProfileManager profileManager;

    public FireworkGadgetListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onFirework(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            Player player = event.getPlayer();
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile.getGadget() != null && profile.getGadget().equals(Gadget.FIREWORK)) {
                if (event.getItem().isSimilar(Gadget.FIREWORK.getGadgetItem())) {
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

                    Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
                    FireworkMeta meta = firework.getFireworkMeta();

                    meta.setPower(2);

                    firework.setFireworkMeta(meta);
                    firework.setPassenger(player);

                    player.spigot().setCollidesWithEntities(false);
                    player.updateInventory();
                }
            }
        }
    }
}
