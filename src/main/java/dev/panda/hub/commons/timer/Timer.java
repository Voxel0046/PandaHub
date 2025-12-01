package dev.panda.hub.commons.timer;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Timer {

    private String name;
    private String prefix;
    private long startMillis;
    private long endMillis;
    private long getRemaining;
    private boolean running;

    public Timer(String name, long startMillis, long endMillis, String prefix) {
        this.name = name;
        this.startMillis = startMillis;
        this.endMillis = endMillis;
        this.prefix = prefix;
        this.running = true;

        TimerTask timerTask = new TimerTask(this);
        timerTask.run();
    }

    public long getRemaining() {
       return endMillis - System.currentTimeMillis();
    }
}
