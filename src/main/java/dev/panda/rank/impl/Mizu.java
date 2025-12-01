package dev.panda.rank.impl;

import dev.panda.rank.*;
import java.util.*;
import com.broustudio.MizuAPI.*;
import org.bukkit.entity.*;

public class Mizu implements IRank, RankObject<String> {


    @Override
    public String getRankSuffix(UUID uuid) {
        return MizuAPI.getAPI().getRankSuffix(this.getRank(uuid));
    }
    
    @Override
    public String getRankSystem() {
        return "MizuCore";
    }
    
    @Override
    public String getRankName(UUID uuid) {
        return this.getRank(uuid);
    }
    
    @Override
    public String getRankColor(UUID uuid) {
        return MizuAPI.getAPI().getRankColor(this.getRank(uuid));
    }
    
    @Override
    public int getRankWeight(UUID uuid) {
        return 0;
    }
    
    @Override
    public String getRealName(Player player) {
        return null;
    }
    
    @Override
    public String getRank(UUID uuid) {
        return MizuAPI.getAPI().getRank(uuid);
    }
    
    @Override
    public String getRankPrefix(UUID uuid) {
        return MizuAPI.getAPI().getRankPrefix(this.getRank(uuid));
    }
}
