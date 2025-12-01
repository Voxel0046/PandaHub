package dev.panda.rank.impl;

import dev.panda.rank.*;
import java.util.*;
import org.bukkit.entity.*;

public class Default implements IRank {

    @Override
    public String getRankPrefix(UUID uuid) {
        return "";
    }
    
    @Override
    public String getRealName(Player player) {
        return null;
    }
    
    @Override
    public String getRankColor(UUID uuid) {
        return "";
    }
    
    @Override
    public String getRankSystem() {
        return "Default";
    }
    
    @Override
    public String getRankSuffix(UUID uuid) {
        return "";
    }
    
    @Override
    public String getRankName(UUID uuid) {
        return "";
    }
    
    @Override
    public int getRankWeight(UUID uuid) {
        return 0;
    }
}
