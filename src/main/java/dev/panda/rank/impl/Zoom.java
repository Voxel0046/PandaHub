package dev.panda.rank.impl;

import dev.panda.rank.*;
import java.util.*;
import club.frozed.core.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Zoom implements IRank
{
    @Override
    public String getRankSuffix(UUID lllllllllllllllIllIIIIlIIlIIIIll) {
        return ZoomAPI.getRankSuffix(this.getPlayer(lllllllllllllllIllIIIIlIIlIIIIll));
    }
    
    @Override
    public String getRankName(UUID lllllllllllllllIllIIIIlIIlIlIIlI) {
        return ZoomAPI.getRankName(this.getPlayer(lllllllllllllllIllIIIIlIIlIlIIlI));
    }
    
    @Override
    public String getRankSystem() {
        return "ZoomCore";
    }
    
    public Player getPlayer(UUID lllllllllllllllIllIIIIlIIIlIllII) {
        return Bukkit.getPlayer(lllllllllllllllIllIIIIlIIIlIllII);
    }
    
    @Override
    public String getRankColor(UUID lllllllllllllllIllIIIIlIIIllllll) {
        return String.valueOf(new StringBuilder().append(ZoomAPI.getRankColor(this.getPlayer(lllllllllllllllIllIIIIlIIIllllll))).append(ZoomAPI.getRankName(this.getPlayer(lllllllllllllllIllIIIIlIIIllllll))));
    }
    
    @Override
    public String getRankPrefix(UUID lllllllllllllllIllIIIIlIIlIIlIIl) {
        return ZoomAPI.getRankPrefix(this.getPlayer(lllllllllllllllIllIIIIlIIlIIlIIl));
    }
    
    @Override
    public String getRealName(Player lllllllllllllllIllIIIIlIIIllIIll) {
        return null;
    }
    
    @Override
    public int getRankWeight(UUID lllllllllllllllIllIIIIlIIIllIlll) {
        return 0;
    }
}
