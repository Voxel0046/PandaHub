package dev.panda.rank.impl;

import dev.panda.rank.*;
import java.util.*;
import me.activated.core.plugin.*;
import org.bukkit.entity.*;
import me.activated.core.api.player.*;

public class AquaCore implements IRank {
    
    @Override
    public String getRankSuffix(UUID uuid) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(uuid);
        return (data == null) ? "No Data" : data.getHighestRank().getSuffix();
    }
    
    @Override
    public String getRankSystem() {
        return "AquaCore";
    }
    
    @Override
    public String getRankPrefix(UUID uuid) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(uuid);
        return (data == null) ? "No Data" : data.getHighestRank().getPrefix();
    }
    
    @Override
    public String getRealName(Player player) {
        return AquaCoreAPI.INSTANCE.getRealName(player);
    }
    
    @Override
    public String getRankName(UUID uuid) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(uuid);
        return (data == null) ? "No Data" : data.getHighestRank().getName();
    }
    
    @Override
    public String getRankColor(UUID uuid) {
        PlayerData data = AquaCoreAPI.INSTANCE.getPlayerData(uuid);
        return (data == null) ? "No Data" : String.valueOf(String.valueOf(data.getHighestRank().getColor()));
    }
    
    @Override
    public int getRankWeight(UUID uuid) {
        GlobalPlayer player = AquaCoreAPI.INSTANCE.getGlobalPlayer(uuid);
        return (player == null) ? 0 : player.getRankWeight();
    }
}
