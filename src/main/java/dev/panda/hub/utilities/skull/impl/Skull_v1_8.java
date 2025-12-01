package dev.panda.hub.utilities.skull.impl;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import dev.panda.hub.utilities.skull.SkullVersion;
import dev.panda.hub.utilities.ChatUtil;
import dev.panda.hub.utilities.compatibility.material.CompatibleMaterial;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class Skull_v1_8 implements SkullVersion {

    @Override
    public ItemStack createCustomSkull(String value, String displayName, List<String> lore) {
        ItemStack skull = new ItemStack(CompatibleMaterial.HUMAN_SKULL.getMaterial(), 1, (short) 3);

        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName(ChatUtil.translate(displayName));
        skullMeta.setLore(ChatUtil.translate(lore));

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", value));

        Field profileField;

        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        }
        catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        skull.setItemMeta(skullMeta);
        return skull;
    }
}
