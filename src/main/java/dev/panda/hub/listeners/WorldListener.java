package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.pvp.PvPManager;
import dev.panda.hub.commons.spawn.SpawnManager;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.compatibility.sound.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WorldListener implements Listener {

    private final ProfileManager profileManager;
    private final SpawnManager spawnManager;
    private final PvPManager pvpManager;

    public WorldListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        this.spawnManager = PandaHub.get().getModuleService().getManagerModule().getSpawnManager();
        this.pvpManager = PandaHub.get().getModuleService().getManagerModule().getPvpManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPlayerWorldJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setGameMode(GameMode.ADVENTURE);

        if (ConfigService.DOUBLE_JUMP_ENABLE) player.setAllowFlight(true);

        if (ConfigService.DISABLE_PLAYER_SEE_IN_HUB) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.hasPermission("pandahub.see")) {
                    player.hidePlayer(online);
                }
            }
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!player.hasPermission("pandahub.see")) {
                    online.hidePlayer(player);
                }
            }
        }

        spawnManager.toSpawn(player, true);

        if (ConfigService.PLAYER_JOIN_SOUND_ENABLE) {
            SoundUtil.play(player, ConfigService.PLAYER_JOIN_SOUND_TYPE);
        }

        if (ConfigService.PLAYER_JOIN_SPEED_ENABLE) {
            player.setWalkSpeed((float) ConfigService.PLAYER_JOIN_SPEED_VALUE);
        }
        else {
            player.setWalkSpeed(0.2F);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockInteract(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock() != null) {
                String blockName = event.getClickedBlock().getType().name();

                if (ConfigService.DENY_BLOCKS_INTERACT.contains(blockName)) {
                    event.setCancelled(true);
                }
                if (ConfigService.ALLOW_BLOCKS_INTERACT.contains(blockName)) {
                    event.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    private void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onVoidTeleport(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                event.setCancelled(true);

                Player player = (Player) event.getEntity();
                Profile profile = profileManager.getProfile(player.getUniqueId());

                if (profile == null) return;

                if (profile.isPvpMode()) {
                    pvpManager.toSpawn(player, true);
                    return;
                }

                spawnManager.toSpawn(player, true);
            }
        }
    }

    @EventHandler
    private void onPhysicsBlock(BlockPhysicsEvent event) {
        event.setCancelled(true);
    }
}
