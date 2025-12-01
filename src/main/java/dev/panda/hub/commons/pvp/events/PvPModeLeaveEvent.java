package dev.panda.hub.commons.pvp.events;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Getter
public class PvPModeLeaveEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private boolean isCancelled;

    public PvPModeLeaveEvent(Player player) {
        this.player = player;
        this.isCancelled = false;
    }

    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
