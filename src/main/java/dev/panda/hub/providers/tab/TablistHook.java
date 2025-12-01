package dev.panda.hub.providers.tab;

import dev.panda.hub.PandaHub;
import dev.panda.hub.providers.TablistProvider;
import dev.panda.hub.services.impl.TablistService;
import lombok.Getter;

public class TablistHook {

    @Getter
    private static Tablist tablist;

    public static void init(PandaHub plugin) {
        if (TablistService.ENABLE) {
            tablist = new Tablist(new TablistProvider(), plugin, 2L);
        }
    }
}
