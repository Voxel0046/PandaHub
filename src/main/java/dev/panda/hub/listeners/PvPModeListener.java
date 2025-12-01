package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.hotbar.CustomHotbarManager;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.commons.pvp.PvPManager;
import dev.panda.hub.commons.pvp.events.PvPModeJoinEvent;
import dev.panda.hub.commons.pvp.events.PvPModeLeaveEvent;
import dev.panda.hub.commons.spawn.SpawnManager;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.TaskUtil;
import dev.panda.hub.utilities.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PvPModeListener implements Listener {

    private final ProfileManager profileManager;
    private final PvPManager pvpManager;
    private final SpawnManager spawnManager;
    private final HotbarManager hotbarManager;
    private final CustomHotbarManager customHotbarManager;

    public PvPModeListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        this.pvpManager = PandaHub.get().getModuleService().getManagerModule().getPvpManager();
        this.spawnManager = PandaHub.get().getModuleService().getManagerModule().getSpawnManager();
        this.hotbarManager = PandaHub.get().getModuleService().getManagerModule().getHotbarManager();
        this.customHotbarManager = PandaHub.get().getModuleService().getManagerModule().getCustomHotbarManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPvPModeJoin(PvPModeJoinEvent event) {
        Player player = event.getPlayer();

        if (player.getPassenger() instanceof Player) {
            ChatUtil.sendMessage(player, "&cYou cannot enter PvP Mode because " + player.getPassenger().getName() + " is above you.");
            return;
        }

        Profile profile = profileManager.getProfile(player.getUniqueId());
        profile.setPvpMode(true);

        if (profile.isOutfitRainbow()) profile.setOutfitRainbow(false);

        player.setAllowFlight(false);
        player.setWalkSpeed(0.2F);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
        player.getInventory().setArmorContents(null);
        player.getInventory().clear();

        pvpManager.toEquip(player);
        pvpManager.toSpawn(player, true);
    }

    @EventHandler
    private void onPvPModeLeave(PvPModeLeaveEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());

        profile.setPvpMode(false);

        if (ConfigService.DOUBLE_JUMP_ENABLE) player.setAllowFlight(true);

        player.setHealth(20);

        spawnManager.toSpawn(player, true);

        player.removePotionEffect(PotionEffectType.SPEED);

        if (ConfigService.PLAYER_JOIN_SPEED_ENABLE) {
            player.setWalkSpeed((float) ConfigService.PLAYER_JOIN_SPEED_VALUE);
        }

        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

        hotbarManager.setHotbar(player);
        customHotbarManager.setCustomHotbar(player);

        if (profile.getOutfit() != null) {
            profile.getOutfit().equipAll(player);
        }
        else {
            player.getInventory().setArmorContents(null);
        }

        player.updateInventory();
    }

    @EventHandler
    private void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        Profile profile = profileManager.getProfile(player.getUniqueId());

        if (profile.isPvpMode()) {
            TaskUtil.runLater(() -> Bukkit.getPluginManager().callEvent(new PvPModeLeaveEvent(player)), 1L);
        }
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.getDrops().clear();
        Player player = event.getEntity();

        if (player.getKiller() != null) {
            Profile killerProfile = PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getKiller().getUniqueId());
            Profile victimProfile = PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId());

            killerProfile.setKills(killerProfile.getKills() + 1);
            killerProfile.setStreak(killerProfile.getStreak() + 1);
            victimProfile.setDeaths(killerProfile.getDeaths() + 1);
            victimProfile.setStreak(0);
        }
    }
}
