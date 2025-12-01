package dev.panda.hub.providers.nametag;

import com.lunarclient.apollo.Apollo;
import com.lunarclient.apollo.module.nametag.Nametag;
import com.lunarclient.apollo.module.nametag.NametagModule;
import com.lunarclient.apollo.recipients.Recipients;
import dev.panda.hub.hooks.PlaceholderAPIHook;
import dev.panda.hub.services.impl.ConfigService;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This source or file may not be used or redistributed without
 * explicit authorization from the developer.
 * © 2025 ~ Eliezer
 */
public class ApolloTask extends BukkitRunnable {

    private final NametagModule nametagModule;
    private final List<String> nametag;

    public ApolloTask() {
        nametagModule = Apollo.getModuleManager().getModule(NametagModule.class);
        nametag = ConfigService.LUNAR_LINES;

        Collections.reverse(nametag); // yea, for some reason Apollo takes the list in a weird way
    }

    @Override
    public void run() {
        for (Player target : Bukkit.getOnlinePlayers()) {
            List<Component> components = nametag.stream()
                    .map(line -> {
                        String processed = line.replace("<player>", target.getName());

                        if (PlaceholderAPIHook.isPlaceholderAPI()) {
                            processed = PlaceholderAPI.setPlaceholders(target, processed);
                        }

                        return Component.text(processed);
                    })
                    .collect(Collectors.toList());

            nametagModule.overrideNametag(Recipients.ofEveryone(), target.getUniqueId(), Nametag.builder()
                    .lines(components)
                    .build()
            );
        }
    }
}