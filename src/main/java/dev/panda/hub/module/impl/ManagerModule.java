package dev.panda.hub.module.impl;

import dev.panda.hub.commons.cosmetics.CosmeticManager;
import dev.panda.hub.commons.hotbar.CustomHotbarManager;
import dev.panda.hub.commons.hotbar.HotbarManager;
import dev.panda.hub.commons.pvp.PvPManager;
import dev.panda.hub.commons.selector.lobby.LobbySelectorManager;
import dev.panda.hub.commons.selector.server.ServerSelectorManager;
import dev.panda.hub.commons.selector.server.SubServerSelectorManager;
import dev.panda.hub.commons.timer.TimerManager;
import dev.panda.hub.services.impl.ConfigService;
import dev.panda.hub.utilities.command.CommandManager;
import dev.panda.hub.utilities.skull.SkullManager;
import dev.panda.hub.PandaHub;
import dev.panda.hub.commons.cosmetics.outfit.OutfitManager;
import dev.panda.hub.commons.queue.QueueManager;
import dev.panda.hub.commons.spawn.SpawnManager;
import dev.panda.hub.module.Module;
import dev.panda.hub.profile.ProfileManager;
import dev.panda.rank.RankManager;
import lombok.Getter;

@Getter
public class ManagerModule extends Module {

    private CommandManager commandManager;
    private RankManager rankManager;

    private ProfileManager profileManager;
    private CosmeticManager cosmeticManager;
    private OutfitManager outfitManager;
    private QueueManager queueManager;
    private PvPManager pvpManager;
    private SpawnManager spawnManager;
    private HotbarManager hotbarManager;
    private CustomHotbarManager customHotbarManager;
    private TimerManager timerManager;
    private ServerSelectorManager serverSelectorManager;
    private SubServerSelectorManager subServerSelectorManager;
    private LobbySelectorManager lobbySelectorManager;
    private SkullManager skullManager;

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void onEnable(PandaHub plugin) {
        this.commandManager = new CommandManager(plugin, ConfigService.DISABLE_COMMAND_COMMANDS);
        this.rankManager = new RankManager(plugin);
        this.profileManager = new ProfileManager();
        this.cosmeticManager = new CosmeticManager();
        this.outfitManager = new OutfitManager();
        this.queueManager = new QueueManager();
        this.pvpManager = new PvPManager();
        this.spawnManager = new SpawnManager();
        this.hotbarManager = new HotbarManager();
        this.customHotbarManager = new CustomHotbarManager();
        this.timerManager = new TimerManager();
        this.serverSelectorManager = new ServerSelectorManager();
        this.subServerSelectorManager = new SubServerSelectorManager();
        this.lobbySelectorManager = new LobbySelectorManager();
        this.skullManager = new SkullManager();

        load(false);
    }

    public void load(boolean reload) {
        skullManager.load();
        serverSelectorManager.load();
        subServerSelectorManager.load();
        lobbySelectorManager.load();
        cosmeticManager.load();
        hotbarManager.load();
        customHotbarManager.load();
        outfitManager.load();
        pvpManager.load();
        spawnManager.load();
        if (reload) {
            hotbarManager.reload();
            customHotbarManager.reload();
        }
    }
}
