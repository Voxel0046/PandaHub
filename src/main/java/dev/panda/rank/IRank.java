package dev.panda.rank;

import java.util.*;
import org.bukkit.entity.*;

public interface IRank {
    String getRankPrefix(UUID uuid);
    int getRankWeight(UUID uuid);
    String getRankSystem();
    String getRankSuffix(UUID uuid);
    String getRankName(UUID uuid);
    String getRankColor(UUID uuid);
    String getRealName(Player player);
}
