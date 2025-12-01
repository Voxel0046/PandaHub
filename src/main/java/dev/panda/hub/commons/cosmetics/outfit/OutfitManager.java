package dev.panda.hub.commons.cosmetics.outfit;

import com.google.common.collect.Maps;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.utilities.item.ItemBuilder;
import dev.panda.hub.PandaHub;
import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class OutfitManager {

    @Getter
    private final Map<String, Outfit> outfits;
    private final FileConfig outfitsFile;

    public OutfitManager() {
        this.outfits = Maps.newHashMap();
        this.outfitsFile = PandaHub.get().getModuleService().getFileModule().getFile("outfits");
    }

    private ItemStack createOutfitPart(ConfigurationSection section, String key, String partName) {
        String[] materialData = section.getString(key + "." + partName + ".MATERIAL").split(":");
        String[] colorData = section.getString(key + "." + partName + ".COLOR").split(":");

        ItemBuilder itemBuilder = new ItemBuilder(Material.matchMaterial(materialData[0]));
        itemBuilder.name(section.getString(key + "." + partName + ".NAME"));

        if (materialData[1] != null) {
            itemBuilder.data(Integer.parseInt(materialData[1]));
        }

        if (colorData.length == 3) {
            itemBuilder.armorColor(Color.fromRGB(Integer.parseInt(colorData[0]), Integer.parseInt(colorData[1]), Integer.parseInt(colorData[2])));
        }

        return itemBuilder.build();
    }

    public void load() {
        outfits.clear();

        ConfigurationSection section = outfitsFile.getConfiguration().getConfigurationSection("OUTFITS");

        if (section == null) return;

        for (String key : section.getKeys(false)) {
            Outfit outfit = new Outfit(key);
            outfit.setHelmet(createOutfitPart(section, key, "HELMET"));
            outfit.setChestplate(createOutfitPart(section, key, "CHESTPLATE"));
            outfit.setLeggings(createOutfitPart(section, key, "LEGGINGS"));
            outfit.setBoots(createOutfitPart(section, key, "BOOTS"));
            outfits.put(key, outfit);
        }
    }
}
