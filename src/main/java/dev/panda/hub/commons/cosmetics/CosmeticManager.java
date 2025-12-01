package dev.panda.hub.commons.cosmetics;

import com.google.common.collect.Maps;
import dev.panda.hub.PandaHub;
import dev.panda.hub.utilities.file.FileConfig;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;

public class CosmeticManager {

    @Getter private final Map<String, Cosmetic> cosmetics;
    private final FileConfig cosmeticConfig;

    public CosmeticManager() {
        this.cosmetics = Maps.newHashMap();
        this.cosmeticConfig = PandaHub.get().getModuleService().getFileModule().getFile("cosmetic");
    }

    public void load() {
        cosmetics.clear();

        ConfigurationSection section = cosmeticConfig.getConfiguration().getConfigurationSection("BUTTONS");

        if (section == null) return;

        for (String key : section.getKeys(false)) {
            Cosmetic cosmetic = new Cosmetic(key);
            cosmetic.setEnable(section.getBoolean(key + ".ENABLE"));
            cosmetic.setDisplayName(section.getString(key + ".ICON.DISPLAYNAME"));
            cosmetic.setDescription(section.getStringList(key + ".ICON.DESCRIPTION"));
            cosmetic.setMaterial(section.getString(key + ".ICON.MATERIAL"));
            cosmetic.setData(section.getInt(key + ".ICON.DATA"));
            cosmetic.setSlot(section.getInt(key + ".ICON.SLOT"));
            cosmetics.put(key, cosmetic);
        }
    }
}
