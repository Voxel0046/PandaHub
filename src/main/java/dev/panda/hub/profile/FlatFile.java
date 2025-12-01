package dev.panda.hub.profile;

import dev.panda.hub.commons.cosmetics.chat_color.ChatColor;
import dev.panda.hub.commons.cosmetics.outfit.Outfit;
import dev.panda.hub.commons.cosmetics.trail.Trail;
import dev.panda.hub.utilities.file.FileConfig;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.gadget.Gadget;
import dev.panda.hub.commons.cosmetics.message_color.MessageColor;
import dev.panda.hub.utilities.TaskUtil;
import org.bukkit.configuration.ConfigurationSection;

public class FlatFile implements IProfile {

    private final FileConfig dataConfig;

    public FlatFile() {
        this.dataConfig = PandaHub.get().getModuleService().getFileModule().getFile("player-data");
    }

    @Override
    public String getProfileSystem() {
        return "FlatFile";
    }

    @Override
    public void save(Profile profile, boolean destroy, boolean async) {
        ConfigurationSection section = dataConfig.getConfiguration().getConfigurationSection("PLAYER_DATA");

        if (section == null) return;

        section.set(profile.getUuid() + ".name", profile.getName());
        section.set(profile.getUuid() + ".pvpMode", profile.isPvpMode());
        section.set(profile.getUuid() + ".buildMode", profile.isBuildMode());
        section.set(profile.getUuid() + ".flyMode", profile.isFlyMode());
        section.set(profile.getUuid() + ".trail", profile.getTrail() == null ? "None" : profile.getTrail().name());
        section.set(profile.getUuid() + ".gadget", profile.getGadget() == null ? "None" : profile.getGadget().name());
        section.set(profile.getUuid() + ".messageColor", profile.getMessageColor() == null ? "None" : profile.getMessageColor().name());
        section.set(profile.getUuid() + ".chatColor", profile.getChatColor() == null ? "None" : profile.getChatColor().name());
        section.set(profile.getUuid() + ".outfit", profile.getOutfit() == null ? "None" : profile.getOutfit().getName());
        section.set(profile.getUuid() + ".outfitRainbow", profile.isOutfitRainbow());
        section.set(profile.getUuid() + ".kills", profile.getKills());
        section.set(profile.getUuid() + ".deaths", profile.getDeaths());
        section.set(profile.getUuid() + ".streak", profile.getStreak());

        if (async) {
            TaskUtil.scheduleSyncDelayedTask(dataConfig::save);
        }
        else {
            dataConfig.save();
        }

        if (destroy) {
            PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfiles().remove(profile.getUuid());
        }
    }

    @Override
    public void load(Profile profile) {
        ConfigurationSection section = dataConfig.getConfiguration().getConfigurationSection("PLAYER_DATA." + profile.getUuid().toString());

        if (section == null) {
            save(profile, false, true);
            return;
        }

        profile.setName(section.getString("name"));
        profile.setPvpMode(section.getBoolean("pvpMode"));
        profile.setBuildMode(section.getBoolean("buildMode"));
        profile.setFlyMode(section.getBoolean("flyMode"));
        profile.setTrail(section.getString("trail").equals("None") ? null : Trail.valueOf(section.getString("trail")));
        profile.setGadget(section.getString("gadget").equals("None") ? null : Gadget.valueOf(section.getString("gadget")));
        profile.setMessageColor(section.getString("messageColor").equals("None") ? null : MessageColor.valueOf(section.getString("messageColor")));
        profile.setChatColor(section.getString("chatColor").equals("None") ? null : ChatColor.valueOf(section.getString("chatColor")));
        profile.setOutfit(section.getString("outfit").equals("None") ? null : Outfit.getOutfit(section.getString("outfit")));
        profile.setOutfitRainbow(section.getBoolean("outfitRainbow"));
        profile.setKills(section.getInt("kills"));
        profile.setDeaths(section.getInt("deaths"));
        profile.setStreak(section.getInt("streak"));
    }
}
