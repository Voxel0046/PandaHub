package dev.panda.hub.hooks;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.chat_color.ChatColor;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.commons.cosmetics.message_color.MessageColor;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.commons.cosmetics.outfit.OutfitManager;
import dev.panda.hub.commons.cosmetics.trail.Trail;
import dev.panda.hub.commons.queue.QueueManager;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.profile.ProfileManager;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PlaceholderAPIExpansion extends PlaceholderExpansion {

    private final ProfileManager profileManager;
    private final QueueManager queueManager;

    public PlaceholderAPIExpansion() {
        this.profileManager = PandaHub.get().getModuleService().getManagerModule().getProfileManager();
        this.queueManager = PandaHub.get().getModuleService().getManagerModule().getQueueManager();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @NotNull String getAuthor() {
        return "";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pandahub";
    }

    @Override
    public @NotNull String getVersion() {
        return PandaHub.get().getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier){
        if (player == null) return "";

        Profile profile = profileManager.getProfile(player.getUniqueId());

        switch (identifier) {
            case "chat_color":
                return profile.getTranslateChatColor();

            case "message_color":
                return profile.getTranslateMessageColor();

            case "queue_name":
                return queueManager.getQueue().isInQueue(player)
                        ? queueManager.getQueue().getServer(player)
                        : "None";

            case "queue_position":
                return queueManager.getQueue().isInQueue(player)
                        ? String.valueOf(queueManager.getQueue().getPosition(player))
                        : "0";

            case "queue_size":
                return queueManager.getQueue().isInQueue(player)
                        ? String.valueOf(queueManager.getQueue().getSize(player))
                        : "0";

            case "trails_total":
                return String.valueOf(Trail.values().length);

            case "trails_owned":
                int ownedTrails = (int) Arrays.stream(Trail.values())
                        .filter(trail -> player.hasPermission(trail.getPermission())
                                || player.hasPermission("pandahub.cosmetics.trail.*"))
                        .count();
                return String.valueOf(ownedTrails);

            case "trails_percentage":
                int totalTrails = Trail.values().length;
                int ownedCount = (int) Arrays.stream(Trail.values())
                        .filter(trail -> player.hasPermission(trail.getPermission())
                                || player.hasPermission("pandahub.cosmetics.trail.*"))
                        .count();
                double percentage = totalTrails == 0 ? 0.0 : ((double) ownedCount / totalTrails) * 100.0;
                return String.format("%.1f%%", percentage);

            case "chatcolors_total":
                return String.valueOf(ChatColor.values().length);

            case "chatcolors_owned":
                int ownedChatColors = (int) Arrays.stream(ChatColor.values())
                        .filter(chatcolor -> player.hasPermission(chatcolor.getPermission())
                                || player.hasPermission("pandahub.cosmetics.chat_color.*"))
                        .count();
                return String.valueOf(ownedChatColors);

            case "chatcolors_percentage":
                int totalChatColors = Trail.values().length;
                int ownedChatColorCount = (int) Arrays.stream(Trail.values())
                        .filter(trail -> player.hasPermission(trail.getPermission())
                                || player.hasPermission("pandahub.cosmetics.chat_color.*"))
                        .count();
                double percentageChatColor = totalChatColors == 0 ? 0.0 : ((double) ownedChatColorCount / totalChatColors) * 100.0;
                return String.format("%.1f%%", percentageChatColor);

            case "messagecolors_total":
                return String.valueOf(MessageColor.values().length);

            case "messagecolors_owned":
                int ownedMessageColors = (int) Arrays.stream(ChatColor.values())
                        .filter(messageColor -> player.hasPermission(messageColor.getPermission())
                                || player.hasPermission("pandahub.settings.message_color.*"))
                        .count();
                return String.valueOf(ownedMessageColors);

            case "messagecolors_percentage":
                int totalMessageColors = Trail.values().length;
                int ownedMessageColorCount = (int) Arrays.stream(Trail.values())
                        .filter(trail -> player.hasPermission(trail.getPermission())
                                || player.hasPermission("pandahub.settings.message_color.*"))
                        .count();
                double percentageMessageColor = totalMessageColors == 0 ? 0.0 : ((double) ownedMessageColorCount / totalMessageColors) * 100.0;
                return String.format("%.1f%%", percentageMessageColor);

            case "gadgets_total":
                return String.valueOf(Gadget.values().length);

            case "gadgets_owned":
                int ownedGadgets = (int) Arrays.stream(ChatColor.values())
                        .filter(gadget -> player.hasPermission(gadget.getPermission())
                                || player.hasPermission("pandahub.cosmetics.gadget.*"))
                        .count();
                return String.valueOf(ownedGadgets);

            case "gadgets_percentage":
                int totalGadgets = Trail.values().length;
                int ownedGadgetCount = (int) Arrays.stream(Trail.values())
                        .filter(trail -> player.hasPermission(trail.getPermission())
                                || player.hasPermission("pandahub.cosmetics.gadget.*"))
                        .count();
                double percentageGadget = totalGadgets == 0 ? 0.0 : ((double) ownedGadgetCount / totalGadgets) * 100.0;
                return String.format("%.1f%%", percentageGadget);

            case "outfits_total":
                OutfitManager outfitManager = PandaHub.get().getModuleService().getManagerModule().getOutfitManager();
                return String.valueOf(outfitManager.getOutfits().size());

            case "outfits_owned":
                outfitManager = PandaHub.get().getModuleService().getManagerModule().getOutfitManager();
                long ownedOutfits = outfitManager.getOutfits().values().stream()
                        .filter(outfit -> player.hasPermission(outfit.getPermission()) || player.hasPermission("pandahub.cosmetics.outfit.*"))
                        .count();
                return String.valueOf(ownedOutfits);

            case "outfits_percentage":
                outfitManager = PandaHub.get().getModuleService().getManagerModule().getOutfitManager();
                int totalOutfits = outfitManager.getOutfits().size();
                long owned = outfitManager.getOutfits().values().stream()
                        .filter(outfit -> player.hasPermission(outfit.getPermission()) || player.hasPermission("pandahub.cosmetics.outfit.*"))
                        .count();
                double percent = totalOutfits == 0 ? 0.0 : ((double) owned / totalOutfits) * 100.0;
                return String.format("%.1f%%", percent);
        }

        return null;
    }

}
