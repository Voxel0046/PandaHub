package dev.panda.hub.commons.cosmetics;

import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.chat_color.ChatColor;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.commons.cosmetics.message_color.MessageColor;
import dev.panda.hub.commons.cosmetics.outfit.OutfitManager;
import dev.panda.hub.commons.cosmetics.trail.Trail;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class Cosmetic {

    private final String name;
    private boolean enable;
    private String displayName;
    private List<String> description;
    private String material;
    private int data;
    private int slot;

    public Cosmetic(String name) {
        this.name = name;
    }

    public ItemStack getIcon(Player player) {
        List<String> updatedLore = description.stream()
                .map(line -> replacePlaceholders(line, player))
                .collect(Collectors.toList());

        return new ItemBuilder(material)
                .data(data)
                .name(displayName)
                .lore(updatedLore)
                .build();
    }

    private String replacePlaceholders(String line, Player player) {
        line = line.replace("%TRAILS_TOTAL%", String.valueOf(Trail.values().length));

        int ownedTrails = (int) Arrays.stream(Trail.values())
                .filter(trail -> player.hasPermission(trail.getPermission())
                        || player.hasPermission("pandahub.cosmetics.trail.*"))
                .count();
        line = line.replace("%TRAILS_OWNED%", String.valueOf(ownedTrails));

        double trailPercentage = Trail.values().length == 0 ? 0.0 : ((double) ownedTrails / Trail.values().length) * 100.0;
        line = line.replace("%TRAILS_PERCENTAGE%", String.format("%.1f%%", trailPercentage));

        line = line.replace("%CHATCOLORS_TOTAL%", String.valueOf(ChatColor.values().length));

        int ownedChatColors = (int) Arrays.stream(ChatColor.values())
                .filter(chatcolor -> player.hasPermission(chatcolor.getPermission())
                        || player.hasPermission("pandahub.cosmetics.chat_color.*"))
                .count();
        line = line.replace("%CHATCOLORS_OWNED%", String.valueOf(ownedChatColors));

        double chatColorPercentage = ChatColor.values().length == 0 ? 0.0 : ((double) ownedChatColors / ChatColor.values().length) * 100.0;
        line = line.replace("%CHATCOLORS_PERCENTAGE%", String.format("%.1f%%", chatColorPercentage));

        line = line.replace("%MESSAGECOLORS_TOTAL%", String.valueOf(MessageColor.values().length));

        int ownedMessageColors = (int) Arrays.stream(MessageColor.values())
                .filter(color -> player.hasPermission(color.getPermission())
                        || player.hasPermission("pandahub.settings.message_color.*"))
                .count();
        line = line.replace("%MESSAGECOLORS_OWNED%", String.valueOf(ownedMessageColors));

        double messageColorPercentage = MessageColor.values().length == 0 ? 0.0 : ((double) ownedMessageColors / MessageColor.values().length) * 100.0;
        line = line.replace("%MESSAGECOLORS_PERCENTAGE%", String.format("%.1f%%", messageColorPercentage));

        line = line.replace("%GADGETS_TOTAL%", String.valueOf(Gadget.values().length));

        int ownedGadgets = (int) Arrays.stream(Gadget.values())
                .filter(gadget -> player.hasPermission(gadget.getPermission())
                        || player.hasPermission("pandahub.cosmetics.gadget.*"))
                .count();
        line = line.replace("%GADGETS_OWNED%", String.valueOf(ownedGadgets));

        double gadgetsPercentage = Gadget.values().length == 0 ? 0.0 : ((double) ownedGadgets / Gadget.values().length) * 100.0;
        line = line.replace("%GADGETS_PERCENTAGE%", String.format("%.1f%%", gadgetsPercentage));

        OutfitManager outfitManager = PandaHub.get().getModuleService().getManagerModule().getOutfitManager();
        int totalOutfits = outfitManager.getOutfits().size();
        long ownedOutfits = outfitManager.getOutfits().values().stream()
                .filter(outfit -> player.hasPermission(outfit.getPermission()) || player.hasPermission("pandahub.cosmetics.outfit.*"))
                .count();

        line = line.replace("%OUTFITS_TOTAL%", String.valueOf(totalOutfits));
        line = line.replace("%OUTFITS_OWNED%", String.valueOf(ownedOutfits));

        double outfitPercentage = totalOutfits == 0 ? 0.0 : ((double) ownedOutfits / totalOutfits) * 100.0;
        line = line.replace("%OUTFITS_PERCENTAGE%", String.format("%.1f%%", outfitPercentage));

        return line;
    }


}
