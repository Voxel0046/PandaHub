package dev.panda.hub.utilities;

import dev.panda.hub.PandaHub;
import dev.panda.hub.hooks.PlaceholderAPIHook;
import dev.panda.hub.profile.Profile;
import dev.panda.hub.services.impl.ConfigService;
import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ChatUtil {

    public String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String[] translate(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = translate(array[i]);
        }
        return array;
    }

    public String placeholder(Player player, String text, boolean colorized) {
        if (colorized) {
            return translate(PlaceholderAPIHook.isPlaceholderAPI() ? PlaceholderAPI.setPlaceholders(player, text) : text);
        }
        else {
            return PlaceholderAPIHook.isPlaceholderAPI() ? PlaceholderAPI.setPlaceholders(player, text) : text;
        }
    }

    public List<String> translate(List<String> list) {
        return list.stream().map(ChatUtil::translate).collect(Collectors.toList());
    }

    public void sendMessage(CommandSender sender, String text) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(translate(text));
        }
        else {
            sender.sendMessage(translate(text));
        }
    }

    public void sendMessage(CommandSender sender, String[] array) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(translate(array));
        }
        else {
            sender.sendMessage(translate(array));
        }
    }

    public String strip(String text) {
        return ChatColor.stripColor(text);
    }

    public void broadcast(String text) {
        Bukkit.broadcastMessage(translate(text));
    }

    public void log(String text) {
        Bukkit.getConsoleSender().sendMessage(translate(text));
    }

    public String capitalize(String str) {
        return WordUtils.capitalize(str);
    }

    public String toReadable(Enum<?> enu) {
        return WordUtils.capitalize(enu.name().replace("_", " ").toLowerCase());
    }

    public String replaced(Player player, String text) {
        Profile profile = PandaHub.get().getModuleService().getManagerModule().getProfileManager().getProfile(player.getUniqueId());

        return text.replace("%PLAYER%", player.getName())
                .replace("%DISCORD%", ConfigService.DISCORD)
                .replace("%STORE%", ConfigService.STORE)
                .replace("%TEAMSPEAK%", ConfigService.TEAMSPEAK)
                .replace("%TWITTER%", ConfigService.TWITTER)
                .replace("%WEBSITE%", ConfigService.WEBSITE)
                .replace("%KILLS%", String.valueOf(profile.getKills()))
                .replace("%DEATHS%", String.valueOf(profile.getDeaths()))
                .replace("%STREAK%", String.valueOf(profile.getStreak()))
                .replace("%RANK%", PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRankName(player.getUniqueId()))
                .replace("%RANK_PREFIX%", PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRankPrefix(player.getUniqueId()))
                .replace("%RANK_SUFFIX%", PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRankSuffix(player.getUniqueId()))
                .replace("%RANK_COLOR%", PandaHub.get().getModuleService().getManagerModule().getRankManager().getRank().getRankColor(player.getUniqueId()))
                .replace("%DATE%", ServerUtil.getDate())
                .replace("%HOUR%", ServerUtil.getHour())
                .replace("%SLOTS%", String.valueOf(Bukkit.getMaxPlayers()));
    }
}
