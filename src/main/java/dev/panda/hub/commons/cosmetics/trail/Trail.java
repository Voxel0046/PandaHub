package dev.panda.hub.commons.cosmetics.trail;

import dev.panda.hub.profile.Profile;
import dev.panda.hub.services.impl.CosmeticService;
import dev.panda.hub.utilities.item.ItemBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor @Getter
public enum Trail {

    HEART("Heart", "Heart", Material.REDSTONE, Effect.HEART),
    FLAME("Flame", "Flame", Material.BLAZE_POWDER, Effect.FLAME),
    SLIME("Slime", "Slime", Material.SLIME_BALL, Effect.SLIME),
    NOTE("Note", "Note", Material.NOTE_BLOCK, Effect.NOTE),
    CLOUD("Cloud", "Cloud", Material.WEB, Effect.CLOUD),
    SMOKE("Smoke", "Smoke", Material.FIREWORK_CHARGE, Effect.LARGE_SMOKE),
    VILLAGER("Villager", "Villager", Material.EMERALD, Effect.HAPPY_VILLAGER),
    CRITICAL("Critical", "Critical", Material.EXP_BOTTLE, Effect.MAGIC_CRIT),
    EXPLOSION("Explosion", "Explosion", Material.TNT, Effect.EXPLOSION),
    FIREWORK("Firework", "Firework", Material.FIREWORK, Effect.FIREWORKS_SPARK),
    LAVA("Lava", "Lava", Material.LAVA_BUCKET, Effect.LAVADRIP),
    WATER("Water", "Water", Material.WATER_BUCKET, Effect.WATERDRIP),
    SNOW("Snow", "Snow", Material.SNOW, Effect.SNOWBALL_BREAK),
    SPELL("Spell", "Spell", Material.POTION, Effect.SPELL);

    private final String name;
    private final String displayName;
    private final Material material;
    private final Effect effect;

    public void playEffect(Player player) {
        player.getWorld().spigot().playEffect(player.getLocation(), effect, 26, 0, 0.2F, 0.5F, 0.2F, 0.2F, 12, 387);
    }

    public String getPermission() {
        return "pandahub.cosmetics.trail." + name.toLowerCase().replace(" ", "_");
    }

    public ItemStack getIcon(Player player, Profile profile) {
        boolean isEquipped = profile.getTrail() != null && profile.getTrail().equals(this);
        boolean hasPermission = player.hasPermission(getPermission()) || player.hasPermission("pandahub.cosmetics.trail.*");

        if (isEquipped) {
            return new ItemBuilder(material)
                    .name(CosmeticService.TRAIL_EQUIPPED_DISPLAYNAME_BUTTON
                            .replace("%TRAIL_NAME%", name)
                            .replace("%TRAIL_DISPLAYNAME%", displayName))
                    .lore(CosmeticService.TRAIL_EQUIPPED_DESCRIPTION_BUTTON)
                    .build();
        } else if (hasPermission) {
            return new ItemBuilder(material)
                    .name(CosmeticService.TRAIL_ALLOWED_DISPLAYNAME_BUTTON
                            .replace("%TRAIL_NAME%", name)
                            .replace("%TRAIL_DISPLAYNAME%", displayName))
                    .lore(CosmeticService.TRAIL_ALLOWED_DESCRIPTION_BUTTON)
                    .build();
        } else {
            return new ItemBuilder(material)
                    .name(CosmeticService.TRAIL_DENIED_DISPLAYNAME_BUTTON
                            .replace("%TRAIL_NAME%", name)
                            .replace("%TRAIL_DISPLAYNAME%", displayName))
                    .lore(CosmeticService.TRAIL_DENIED_DESCRIPTION_BUTTON)
                    .build();
        }
    }

}
