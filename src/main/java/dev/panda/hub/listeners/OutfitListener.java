package dev.panda.hub.listeners;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.commons.cosmetics.outfit.OutfitManager;
import dev.panda.hub.commons.cosmetics.outfit.OutfitRainbow;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OutfitListener implements Listener {

    private final ProfileManager profileManager;
    private final OutfitManager outfitManager;

    public OutfitListener(PandaHub plugin) {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        this.outfitManager = PandaHub.get().getModuleService().getManagerModule().getOutfitManager();
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Bukkit.getScheduler().runTaskLater(PandaHub.get(), () -> {
            Profile profile = profileManager.getProfile(player.getUniqueId());

            if (profile == null) return;

            if (profile.getOutfit() != null) {
                if (profile.getOutfit().getName().equalsIgnoreCase("rainbow") && profile.isOutfitRainbow()) {
                    OutfitRainbow armor = new OutfitRainbow(player, 0, 0, 255, 0, 0, 0, 0, 0, 0);
                    armor.runTaskTimerAsynchronously(PandaHub.get(), 0L, 1L);
                    return;
                }

                profile.getOutfit().equipAll(player);
            } else {
                for (Outfit outfit : outfitManager.getOutfits().values()) {
                    if (!player.isOp() && player.hasPermission(outfit.getPermission())) {
                        outfit.equipAll(player);
                        profile.setOutfit(outfit);
                        break;
                    }
                }
            }
        }, 20L);
    }

}
