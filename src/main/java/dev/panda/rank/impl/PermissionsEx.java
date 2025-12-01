package dev.panda.rank.impl;

import dev.panda.rank.*;
import net.milkbowl.vault.chat.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.plugin.*;

public class PermissionsEx implements IRank {

    private Chat chat;
    
    @Override
    public int getRankWeight(UUID uuid) {
        return 0;
    }
    
    @Override
    public String getRankSuffix(UUID uuid) {
        return this.chat.getPlayerSuffix(this.getPlayer(uuid));
    }
    
    private Player getPlayer(UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }
    
    @Override
    public String getRankColor(UUID uuid) {
        return "";
    }
    
    public PermissionsEx() {
        RegisteredServiceProvider<Chat> chat = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        if (chat != null) {
            this.chat = chat.getProvider();
        }
    }
    
    @Override
    public String getRealName(Player player) {
        return null;
    }
    
    @Override
    public String getRankSystem() {
        return "PermissionsEx";
    }
    
    @Override
    public String getRankPrefix(UUID uuid) {
        return this.chat.getPlayerPrefix(this.getPlayer(uuid));
    }
    
    @Override
    public String getRankName(UUID uuid) {
        return this.chat.getPrimaryGroup(this.getPlayer(uuid));
    }
}
