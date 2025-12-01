package dev.panda.hub.commons.timer;

import dev.panda.hub.PandaHub;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public class TimerTask implements Runnable {

    private final Timer timer;
    private final int id;

    public TimerTask(Timer timer) {
        this.timer = timer;
        this.id = Bukkit.getScheduler().runTaskTimerAsynchronously(PandaHub.get(), this, 0L, 20L).getTaskId();
    }

    @Override
    public void run() {
        if (timer == null) {
            this.cancel();
            return;
        }
        if (timer.getEndMillis() < System.currentTimeMillis()) {
            this.cancel();
            return;
        }
        if (!timer.isRunning()) {
            this.cancel();
        }
    }

    private void cancel() {
        PandaHub.get().getModuleService().getManagerModule().getTimerManager().deleteTimer(timer);
        Bukkit.getScheduler().cancelTask(id);
    }
}
