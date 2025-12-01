package dev.panda.hub.listeners.hotbar;

import dev.panda.hub.commons.hotbar.Hotbar;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.compatibility.material.CompatibleMaterial;
import dev.panda.hub.utilities.compatibility.sound.SoundUtil;
import dev.panda.hub.PandaHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spigotmc.event.entity.EntityDismountEvent;

public class EnderButtHotbarListener implements Listener {

    private HotbarManager hotbarManager;

    public EnderButtHotbarListener(PandaHub plugin) {
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onEnderButt(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            if (event.getItem() == null) return;

            Hotbar enderButt = hotbarManager.getHotbar("ENDER_BUTT");

            if (enderButt.isEnable() && enderButt.isHotbarItem(event.getItem())) {
                event.setCancelled(true);

                Player player = event.getPlayer();

                if (ConfigService.ENDER_BUTT_RIDE) {
                    if (player.isSneaking()) {
                        player.updateInventory();
                        return;
                    }

                    if (player.getVehicle() != null) {
                        player.getVehicle().remove();
                        player.eject();
                    }

                    Projectile projectile;

                    if (event.getItem().getType().equals(CompatibleMaterial.SNOW_BALL.getMaterial())) {
                        projectile = player.launchProjectile(Snowball.class);
                    }
                    else {
                        projectile = player.launchProjectile(EnderPearl.class);
                    }

                    projectile.setVelocity(player.getLocation().getDirection());
                    projectile.setPassenger(player);
                    player.spigot().setCollidesWithEntities(false);
                }
                else {
                    player.setVelocity(player.getLocation().getDirection().normalize().setY(ConfigService.ENDER_BUTT_VELOCITY));
                    player.setVelocity(player.getLocation().getDirection().normalize().multiply(2F));
                }

                player.updateInventory();
                SoundUtil.play(player, ConfigService.ENDER_BUTT_SOUND);
            }
        }
    }

    @EventHandler
    private void onEnderButtTeleport(PlayerTeleportEvent event) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (event.getEntity() instanceof Player) {
            event.getDismounted().remove();
        }
    }
}
